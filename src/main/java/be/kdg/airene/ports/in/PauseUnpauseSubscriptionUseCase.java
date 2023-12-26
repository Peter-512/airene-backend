package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface PauseUnpauseSubscriptionUseCase {
	Optional<Subscription> togglePauseSubscription(UUID userId, UUID subscriptionId);
}
