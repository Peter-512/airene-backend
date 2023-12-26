package be.kdg.airene.core;

import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.GetUserSubscriptionsUseCase;
import be.kdg.airene.ports.in.LoadSubscriptionsByUserIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetUserSubscriptionsUseCase implements GetUserSubscriptionsUseCase {

	private final LoadSubscriptionsByUserIdPort loadSubscriptionsByUserIdPort;

	@Override
	public Set<Subscription> getUserSubscriptions(UUID userId, String sort) {
		Set<Subscription> subscriptions = loadSubscriptionsByUserIdPort.loadSubscriptionsByUserId(userId, sort);
		log.debug("User with id {} has {} subscriptions", userId, subscriptions.size());
		return subscriptions;
	}
}
