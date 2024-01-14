package be.kdg.airene.core;



import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.notification.Notification;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.AnomalyDetectedUseCase;
import be.kdg.airene.ports.out.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultAnomalyDetectedUseCase implements AnomalyDetectedUseCase {
	private final AnomalySavePort anomalySavePort;
	private final AnomalyLoadPort anomalyLoadPort;
	private final NotificationSavePort saveNotificationPort;
	private final LoadDataByIdPort loadDataByPredictionIdPort;
	private final NearestSubscriptionsLoadPort nearestSubscriptionsLoadPort;
	private List<AnomalyNotifyPort> anomalyNotifyPorts;

	@Override
	public void anomalyDetected(Prediction prediction) {
		if (!prediction.isAnomaly()) return;
		log.debug("Anomaly detected at location: {}", prediction.getLocation());
		Location location = prediction.getLocation();
		Set<Subscription> subscriptions = nearestSubscriptionsLoadPort.loadNearestSubscriptions(location.getLatitude(), location.getLongitude());
		log.debug("Found {} subscriptions near anomaly detected at location: {}", subscriptions.size(), location);
		Anomaly anomalyDetection = Anomaly.anomalyDetected(prediction);
		Anomaly anomaly = anomalySavePort.saveAnomaly(anomalyDetection);
		Optional<Data> optData = loadDataByPredictionIdPort.loadDataById(prediction.getDataId());
		if (subscriptions.isEmpty()) {
			log.info("No users subscribed to anomaly detected at location: {}", location);
			return;
		}
		if (optData.isEmpty()) {
			log.error("No data found for anomaly detected at location: {}", location);
			return;
		}
		Data data = optData.get();
		subscriptions.parallelStream().filter(Subscription::isEnabled)
		             .forEach(subscription -> {
			             Notification notification = Notification.notify(subscription.getUser()
			                                                                         .getId(), anomaly, anomaly.getDataId());
			             saveNotificationPort.saveNotification(notification);
		             });
		anomalyNotifyPorts.parallelStream()
		                  .forEach(anomalyNotifyPort -> anomalyNotifyPort.notifyAnomaly(List.copyOf(subscriptions), data));
	}
}
