package be.kdg.airene.ports.in;

import be.kdg.airene.domain.location.SubscribedLocation;
import be.kdg.airene.domain.subscription.Subscription;

import java.util.UUID;

@FunctionalInterface
public interface SubscribeToLocationUseCase {
	Subscription subscribeToLocation(UUID userID, SubscribedLocation location);
}
