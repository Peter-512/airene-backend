package be.kdg.airene.adapters.out.db.prediction;

import be.kdg.airene.adapters.out.mapper.PredictionMapper;
import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.ports.out.SavePredictionsPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class PredictionAdapter implements SavePredictionsPort {

	private final PredictionRepository predictionRepository;
	private final PredictionMapper predictionMapper = PredictionMapper.INSTANCE;


	@Override
	public void savePredictions(Collection<Prediction> predictions) {
		predictionRepository.saveAll(predictionMapper.toJPA(predictions));
	}
}
