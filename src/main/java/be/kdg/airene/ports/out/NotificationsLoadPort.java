package be.kdg.airene.ports.out;

import be.kdg.airene.domain.notification.Notification;

import java.util.Optional;
import java.util.UUID;

public interface NotificationsLoadPort {
	Optional<Notification> loadNotificationById(UUID anomalyId);
}
