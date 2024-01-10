package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.domain.feedback.FeedbackReason;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {
	private FeedbackReason feedbackReason;
	private String description;
}
