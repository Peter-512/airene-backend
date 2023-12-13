package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

import java.util.Set;
import java.util.UUID;

public interface GetUserSubscriptionsUseCase {
	Set<Subscription> getUserSubscriptions(UUID userId);
}
