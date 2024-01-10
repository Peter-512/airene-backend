package be.kdg.airene.adapters.out.db.notification;

import be.kdg.airene.adapters.out.db.anomaly.AnomalyJPA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID userId;
	@ManyToOne
	@JoinColumn(name = "anomaly_id")
	private AnomalyJPA anomaly;
	private UUID dataId;
	private boolean hasProvidedFeedback;
	private LocalDateTime timestamp;
}
