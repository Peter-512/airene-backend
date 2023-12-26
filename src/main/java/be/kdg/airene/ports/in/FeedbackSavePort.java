package be.kdg.airene.ports.in;

import be.kdg.airene.domain.feedback.Feedback;

@FunctionalInterface
public interface FeedbackSavePort {
	Feedback saveFeedback(Feedback feedback);
}
