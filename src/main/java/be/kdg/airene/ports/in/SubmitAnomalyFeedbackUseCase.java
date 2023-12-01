package be.kdg.airene.ports.in;

import be.kdg.airene.domain.anomaly.AnomalyID;
import be.kdg.airene.domain.feedback.Feedback;

@FunctionalInterface
public interface SubmitAnomalyFeedbackUseCase {
	void submitAnomalyFeedback(AnomalyID anomalyID, Feedback feedback);
}
