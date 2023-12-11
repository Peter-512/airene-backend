package be.kdg.airene.ports.in;

import be.kdg.airene.domain.anomaly.Anomaly;

@FunctionalInterface
public interface AnomalySavePort {
	Anomaly saveAnomaly(Anomaly anomalyDetection);
}
