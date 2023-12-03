package be.kdg.airene.domain.anomaly;

import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Anomaly implements Serializable{
	private UUID id;
	private Timestamp timestamp;
	private List<Feedback> feedbackList;
	private Location location;
	private Map<String, Object> data; 	// rest of the data-entry

	private Anomaly(UUID id, Timestamp timestamp) {
		this.id = id;
		this.timestamp = timestamp;
		this.location = new Location((double) data.get("latitude"), (double) data.get("longitude"));
	}

	public static Anomaly anomalyDetected(UUID id, Timestamp timestamp) {
		return new Anomaly(id, timestamp);
	}

	public void addFeedback(Feedback feedback) {
		feedbackList.add(feedback);
	}
}
