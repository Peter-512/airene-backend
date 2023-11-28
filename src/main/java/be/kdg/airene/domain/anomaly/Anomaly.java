package be.kdg.airene.domain.anomaly;

import be.kdg.airene.domain.Location;
import be.kdg.airene.domain.feedback.Feedback;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Anomaly {
	private AnomalyID anomalyID;
	private LocalDateTime timestamp;
	private Location location;
	private List<Feedback> feedbackList;

	private Anomaly(LocalDateTime timestamp, Location location) {
		this.anomalyID = new AnomalyID(UUID.randomUUID());
		this.timestamp = timestamp;
		this.location = location;
	}

	public static Anomaly anomalyDetected(Location location, LocalDateTime timestamp) {
		return new Anomaly(timestamp, location);
	}

	public void addFeedback(Feedback feedback) {
		feedbackList.add(feedback);
	}
}
