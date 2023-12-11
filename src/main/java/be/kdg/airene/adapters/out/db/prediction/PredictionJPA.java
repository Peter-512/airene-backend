package be.kdg.airene.adapters.out.db.prediction;

import be.kdg.airene.adapters.out.db.data.LocationJPA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "predictions")
@Getter
@Setter
public class PredictionJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	private LocationJPA location;
	private UUID dataId;
	@Column(name = "average_regression")
	private double averageRegression;
	@Column(name = "is_anomaly")
	private boolean isAnomaly;
}
