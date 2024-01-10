package be.kdg.airene.ports.out;

import be.kdg.airene.domain.notification.Notification;

@FunctionalInterface
public interface NotificationSavePort {
	void saveNotification(Notification notification);
}
