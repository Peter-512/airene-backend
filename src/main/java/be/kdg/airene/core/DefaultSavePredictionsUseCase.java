package be.kdg.airene.core;

import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.ports.in.SavePredictionsUseCase;
import be.kdg.airene.ports.out.SavePredictionsPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class DefaultSavePredictionsUseCase implements SavePredictionsUseCase {
	private final SavePredictionsPort savePredictionsPort;
	@Override
	public void savePredictions(Collection<Prediction> predictions) {
		savePredictionsPort.savePredictions(predictions);
	}
}
