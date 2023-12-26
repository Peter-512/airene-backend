package be.kdg.airene.domain.notification;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Notification {
	private UUID id;
	private UUID userId;
	private UUID anomalyId;
	private UUID dataId;
	private LocalDateTime timestamp = LocalDateTime.now();
	private boolean hasProvidedFeedback = false;

	public static Notification notify(UUID userId, UUID anomalyId, UUID dataId) {
		Notification notification = new Notification();
		notification.setUserId(userId);
		notification.setAnomalyId(anomalyId);
		notification.setDataId(dataId);
		return notification;
	}
}
