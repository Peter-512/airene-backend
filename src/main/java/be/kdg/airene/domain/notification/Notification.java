package be.kdg.airene.domain.notification;

import be.kdg.airene.domain.anomaly.Anomaly;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Notification {
	private UUID id;
	private UUID userId;
	private Anomaly anomaly;
	private boolean hasProvidedFeedback;
}
