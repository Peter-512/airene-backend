package be.kdg.airene.adapters.out.db.anomaly;

import be.kdg.airene.adapters.out.db.data.LocationJPA;
import be.kdg.airene.adapters.out.db.feedback.FeedbackJPA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "anomalies")
@Getter
@Setter
public class AnomalyJPA {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	private LocationJPA location;
	private UUID dataId;
	@Column(name = "average_regression")
	private double averageRegression;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "anomaly")
	private List<FeedbackJPA> feedback;
}
