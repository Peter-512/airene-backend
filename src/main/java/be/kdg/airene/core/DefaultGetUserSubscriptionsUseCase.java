package be.kdg.airene.core;

import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.GetUserSubscriptionsUseCase;
import be.kdg.airene.ports.in.LoadSubscriptionsByUserIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetUserSubscriptionsUseCase implements GetUserSubscriptionsUseCase {

	private final LoadSubscriptionsByUserIdPort loadSubscriptionsByUserIdPort;

	@Override
	public List<Subscription> getUserSubscriptions(UUID userId) {
		List<Subscription> subscriptions = loadSubscriptionsByUserIdPort.loadSubscriptionsByUserId(userId);
		log.debug("User with id {} has {} subscriptions", userId, subscriptions.size());
		return subscriptions;
	}
}
