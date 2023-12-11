package be.kdg.airene.adapters.out.db.feedback;

import be.kdg.airene.domain.feedback.FeedbackReason;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FeedbackJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private FeedbackReason feedbackReason;
	private String description; // optional
}
