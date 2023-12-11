package be.kdg.airene.core;

import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.in.AnomalyDetectedUseCase;
import be.kdg.airene.ports.in.AnomalySavePort;
import be.kdg.airene.ports.in.NearestUsersLoadPort;
import be.kdg.airene.ports.out.AnomalyNotifyPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultAnomalyDetectedUseCase implements AnomalyDetectedUseCase {
	private final AnomalySavePort anomalySavePort;
	private List<AnomalyNotifyPort> anomalyNotifyPorts;
	private final NearestUsersLoadPort nearestUsersLoadPort;

	@Override
	public void anomalyDetected(Prediction prediction) {
		if (!prediction.isAnomaly()) return;
		Location location = prediction.getLocation();
		List<User> users = nearestUsersLoadPort.loadUsersSubscribedToNearestAnomaly(location.getLatitude(), location.getLongitude());
		Anomaly anomaly = anomalySavePort.saveAnomaly(Anomaly.anomalyDetected(prediction));
		if (users.isEmpty()) {
			log.info("No users subscribed to anomaly detected at location: {}", location);
			return;
		}
		anomalyNotifyPorts.forEach(anomalyNotifyPort -> anomalyNotifyPort.notifyAnomaly(users, anomaly));
	}
}
