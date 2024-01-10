package be.kdg.airene.adapters.out.db.feedback;

import be.kdg.airene.domain.feedback.FeedbackReason;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class FeedbackJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private FeedbackReason feedbackReason;
	private String description; // optional
	private UUID anomalyId;
}
