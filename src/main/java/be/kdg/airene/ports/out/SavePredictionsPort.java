package be.kdg.airene.ports.out;

import be.kdg.airene.domain.anomaly.Prediction;

import java.util.Collection;

@FunctionalInterface
public interface SavePredictionsPort {
	void savePredictions(Collection<Prediction> predictions);
}
