package be.kdg.airene.domain.feedback;

import be.kdg.airene.domain.anomaly.Anomaly;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback {
	private Long id;
	private Anomaly anomaly;
	private FeedbackReason feedbackReason;
	private String description; // optional

	public Feedback(FeedbackReason feedbackReason, String description) {
		this.feedbackReason = feedbackReason;
		this.description = description;
	}

	public static Feedback submitFeedback(FeedbackReason feedbackReason, String description) {
		return new Feedback(feedbackReason, description);
	}
}
