package be.kdg.airene.ports.in;

import be.kdg.airene.domain.feedback.Feedback;

import java.util.UUID;

@FunctionalInterface
public interface SubmitAnomalyFeedbackUseCase {
	void submitAnomalyFeedback(UUID notificationId, UUID anomalyId, Feedback feedback);
}
