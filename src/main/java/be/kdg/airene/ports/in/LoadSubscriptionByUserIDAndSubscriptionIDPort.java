package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadSubscriptionByUserIDAndSubscriptionIDPort {
	Optional<Subscription> loadSubscriptionByUserIDAndSubscriptionID(UUID userID, UUID subscriptionID);
}
