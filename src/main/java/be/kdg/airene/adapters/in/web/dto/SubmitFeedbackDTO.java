package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.domain.feedback.FeedbackReason;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmitFeedbackDTO {
	@NotNull
	private FeedbackReason feedbackReason;
	private String description;
}
