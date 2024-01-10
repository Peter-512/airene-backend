package be.kdg.airene.ports.out;

import be.kdg.airene.domain.anomaly.Anomaly;

@FunctionalInterface
public interface AnomalySavePort {
	Anomaly saveAnomaly(Anomaly anomalyDetection);
}
