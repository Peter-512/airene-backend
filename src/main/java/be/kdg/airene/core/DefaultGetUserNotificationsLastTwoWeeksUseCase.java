package be.kdg.airene.core;

import be.kdg.airene.domain.notification.Notification;
import be.kdg.airene.ports.in.GetUserNotificationsLastTwoWeeksUseCase;
import be.kdg.airene.ports.in.LoadNotificationsLastTwoWeeksByUserIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetUserNotificationsLastTwoWeeksUseCase implements GetUserNotificationsLastTwoWeeksUseCase {

	LoadNotificationsLastTwoWeeksByUserIdPort loadNotificationsLastTwoWeeksByUserIdPort;

	@Override
	public List<Notification> getUserNotificationsLastTwoWeeks(UUID userId, String sort) {
		return loadNotificationsLastTwoWeeksByUserIdPort.loadNotificationsLastTwoWeeksByUserId(userId, sort);
	}
}
