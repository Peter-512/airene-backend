package be.kdg.airene.core;

import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.ports.in.AnomalyLoadPort;
import be.kdg.airene.ports.in.AnomalySavePort;
import be.kdg.airene.ports.in.SubmitAnomalyFeedbackUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultSubmitAnomalyFeedbackUseCase implements SubmitAnomalyFeedbackUseCase {

	private final AnomalyLoadPort anomalyLoadPort;
	private final AnomalySavePort anomalySavePort;

	@Override
	public void submitAnomalyFeedback(UUID anomalyId, Feedback feedback) {
		anomalyLoadPort.loadAnomaly(anomalyId).ifPresentOrElse(
				anomalyDetection -> {
					anomalyDetection.submitFeedback(feedback);
					anomalySavePort.saveAnomaly(anomalyDetection);
					log.debug("Feedback added to anomaly detection with id: " + anomalyId);
				},
				() -> log.error("Anomaly detection with id: " + anomalyId + " not found")
		);
	}
}
