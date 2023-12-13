package be.kdg.airene.core;

import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.AnomalyDetectedUseCase;
import be.kdg.airene.ports.in.AnomalySavePort;
import be.kdg.airene.ports.in.LoadDataByIdPort;
import be.kdg.airene.ports.in.NearestSubscriptionsLoadPort;
import be.kdg.airene.ports.out.AnomalyNotifyPort;
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
	private List<AnomalyNotifyPort> anomalyNotifyPorts;
	private final LoadDataByIdPort loadDataByPredictionIdPort;
	private final NearestSubscriptionsLoadPort nearestSubscriptionsLoadPort;

	@Override
	public void anomalyDetected(Prediction prediction) {
		if (!prediction.isAnomaly()) return;
		log.debug("Anomaly detected at location: {}", prediction.getLocation());
		Location location = prediction.getLocation();
		Set<Subscription> subscriptions = nearestSubscriptionsLoadPort.loadNearestSubscriptions(location.getLatitude(), location.getLongitude());
		log.debug("Found {} subscriptions near anomaly detected at location: {}", subscriptions.size(), location);
		anomalySavePort.saveAnomaly(Anomaly.anomalyDetected(prediction));
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
		anomalyNotifyPorts.forEach(anomalyNotifyPort -> anomalyNotifyPort.notifyAnomaly(List.copyOf(subscriptions), data));
	}
}
