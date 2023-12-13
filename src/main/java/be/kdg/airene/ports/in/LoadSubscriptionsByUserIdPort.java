package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface LoadSubscriptionsByUserIdPort {
	List<Subscription> loadSubscriptionsByUserId(UUID userId);
}
