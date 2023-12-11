package be.kdg.airene.ports.in;

import be.kdg.airene.domain.anomaly.Prediction;

@FunctionalInterface
public interface AnomalyDetectedUseCase {
	void anomalyDetected(Prediction prediction);
}
