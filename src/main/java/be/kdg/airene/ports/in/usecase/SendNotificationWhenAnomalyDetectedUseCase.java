package be.kdg.airene.ports.in.usecase;

import be.kdg.airene.domain.anomaly.Anomaly;

@FunctionalInterface
public interface SendNotificationWhenAnomalyDetectedUseCase {
	void sendNotificationWhenAnomalyDetected(Anomaly anomaly);
}
