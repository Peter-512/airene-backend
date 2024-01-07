package be.kdg.airene.adapters.out.db.notification;

import be.kdg.airene.adapters.out.mapper.NotificationMapper;
import be.kdg.airene.domain.notification.Notification;
import be.kdg.airene.ports.in.LoadNotificationsLastTwoWeeksByUserIdPort;
import be.kdg.airene.ports.in.NotificationSavePort;
import be.kdg.airene.ports.in.NotificationsLoadPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationAdapter implements NotificationSavePort, LoadNotificationsLastTwoWeeksByUserIdPort, NotificationsLoadPort {

	private final NotificationRepository notificationRepository;
	private final NotificationMapper notificationMapper = NotificationMapper.INSTANCE;

	@Override
	public void saveNotification(Notification notification) {
		notificationRepository.save(
				notificationMapper.toJPA(notification)
		);
	}

	@Override
	public List<Notification> loadNotificationsLastTwoWeeksByUserId(UUID userId, String sort) {
		Sort sortBy = sort.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("timestamp")
		                                                                     .ascending() : Sort.by("timestamp")
		                                                                                        .descending();
		return notificationMapper.toDomain(notificationRepository.findNotificationsByUserId(userId, sortBy));
	}

	@Override
	public Optional<Notification> loadNotificationByAnomalyId(UUID anomalyId) {
		return notificationRepository.findByAnomalyId(anomalyId).map(notificationMapper::toDomain);
	}
}
