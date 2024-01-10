package be.kdg.airene.adapters.in.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotificationDTO {
	private UUID id;
	private AnomalyDTO anomaly;
	private UUID dataId;
	private boolean hasProvidedFeedback;
	private LocalDateTime timestamp;
}
