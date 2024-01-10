package be.kdg.airene.ports.out;

import be.kdg.airene.domain.feedback.Feedback;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface LoadFeedbackByAnomalyIdPort {
	List<Feedback> loadFeedbackByAnomalyId(UUID anomalyId);
}
