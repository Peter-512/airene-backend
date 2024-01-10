package be.kdg.airene.core;

import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.ports.in.SubmitAnomalyFeedbackUseCase;
import be.kdg.airene.ports.out.AnomalyLoadPort;
import be.kdg.airene.ports.out.FeedbackSavePort;
import be.kdg.airene.ports.out.NotificationSavePort;
import be.kdg.airene.ports.out.NotificationsLoadPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultSubmitAnomalyFeedbackUseCase implements SubmitAnomalyFeedbackUseCase {

	private final AnomalyLoadPort anomalyLoadPort;
	private final FeedbackSavePort feedbackSavePort;
	private final NotificationsLoadPort notificationsLoadPort;
	private final NotificationSavePort notificationsSavePort;

	@Override
	@Transactional
	public void submitAnomalyFeedback(UUID anomalyId, Feedback feedback) {
		anomalyLoadPort.loadAnomaly(anomalyId).ifPresentOrElse(
				anomalyDetection -> {
					Feedback submitted = anomalyDetection.submitFeedback(feedback);
					feedbackSavePort.saveFeedback(submitted);
					log.debug("Feedback added to anomaly detection with id: " + anomalyId);
				},
				() -> log.error("Anomaly detection with id: " + anomalyId + " not found")
		);
		notificationsLoadPort.loadNotificationByAnomalyId(anomalyId).ifPresentOrElse(
				notification -> {
					notification.setHasProvidedFeedback(true);
					notificationsSavePort.saveNotification(notification);
					log.debug("Notification with id: " + anomalyId + " marked as read");
				},
				() -> log.error("Notification with id: " + anomalyId + " not found"));
	}
}
