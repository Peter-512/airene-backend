package be.kdg.airene.domain.notification;

import be.kdg.airene.domain.anomaly.Anomaly;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Notification {
	private UUID id;
	private UUID userId;
	private Anomaly anomaly;
	private UUID dataId;
	private LocalDateTime timestamp = LocalDateTime.now();
	private boolean hasProvidedFeedback = false;

	public static Notification notify(UUID userId, Anomaly anomaly, UUID dataId) {
		Notification notification = new Notification();
		notification.setUserId(userId);
		notification.setAnomaly(anomaly);
		notification.setDataId(dataId);
		return notification;
	}
}
