package be.kdg.airene.core;

import be.kdg.airene.domain.notification.Notification;
import be.kdg.airene.ports.in.GetUserNotificationsLastTwoWeeksUseCase;
import be.kdg.airene.ports.out.LoadFeedbackByAnomalyIdPort;
import be.kdg.airene.ports.out.LoadNotificationsLastTwoWeeksByUserIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetUserNotificationsLastTwoWeeksUseCase implements GetUserNotificationsLastTwoWeeksUseCase {

	private final LoadNotificationsLastTwoWeeksByUserIdPort loadNotificationsLastTwoWeeksByUserIdPort;
	private final LoadFeedbackByAnomalyIdPort loadFeedbackByAnomalyIdPort;

	@Override
	public List<Notification> getUserNotificationsLastTwoWeeks(UUID userId, String sort) {
		List<Notification> notifications = loadNotificationsLastTwoWeeksByUserIdPort.loadNotificationsLastTwoWeeksByUserId(userId, sort);
		notifications.forEach(notification -> {
			if (!notification.isHasProvidedFeedback()) {
				return;
			}
			notification.getAnomaly()
			            .setFeedback(loadFeedbackByAnomalyIdPort.loadFeedbackByAnomalyId(notification.getAnomaly()
			                                                                                     .getId()));
		});
		return notifications;
	}
}
