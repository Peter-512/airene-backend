package be.kdg.airene.ports.in;

import be.kdg.airene.domain.notification.Notification;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface GetUserNotificationsLastTwoWeeksUseCase {
	List<Notification> getUserNotificationsLastTwoWeeks(UUID userId, String sort);
}
