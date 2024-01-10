package be.kdg.airene.ports.out;

import be.kdg.airene.domain.notification.Notification;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface LoadNotificationsLastTwoWeeksByUserIdPort {
	List<Notification> loadNotificationsLastTwoWeeksByUserId(UUID userId, String sort);
}
