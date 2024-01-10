package be.kdg.airene.ports.out;

import be.kdg.airene.domain.feedback.Feedback;

@FunctionalInterface
public interface FeedbackSavePort {
	Feedback saveFeedback(Feedback feedback);
}
