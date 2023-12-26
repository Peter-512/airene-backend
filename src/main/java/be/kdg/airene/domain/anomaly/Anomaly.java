package be.kdg.airene.domain.anomaly;

import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Anomaly {
	private UUID id;
	private LocalDateTime timestamp;
	private UUID dataId;
	private Location location;
	private double averageRegression;
	private List<Feedback> feedbackList = new ArrayList<>();

	public static Anomaly anomalyDetected(Prediction prediction) {
		Anomaly anomaly = new Anomaly();
		anomaly.setId(prediction.getId());
		anomaly.setTimestamp(prediction.getTimestamp());
		anomaly.setDataId(prediction.getDataId());
		anomaly.setLocation(prediction.getLocation());
		anomaly.setAverageRegression(prediction.getAverageRegression());
		return anomaly;
	}

	public Feedback submitFeedback(Feedback feedback) {
		feedback.setAnomaly(this);
		this.feedbackList.add(feedback);
		return feedback;
	}
}
