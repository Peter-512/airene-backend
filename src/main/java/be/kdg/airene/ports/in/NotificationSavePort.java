package be.kdg.airene.ports.in;

import be.kdg.airene.domain.notification.Notification;

@FunctionalInterface
public interface NotificationSavePort {
	void saveNotification(Notification notification);
}
