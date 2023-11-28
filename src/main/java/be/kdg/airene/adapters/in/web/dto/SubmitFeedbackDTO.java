package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.domain.feedback.FeedbackReason;
import lombok.Data;

@Data
public class SubmitFeedbackDTO {

	private FeedbackReason feedbackReason;
	private String description;
}
