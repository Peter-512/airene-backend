package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

import java.util.List;
import java.util.UUID;

public interface GetUserSubscriptionsUseCase {
	List<Subscription> getUserSubscriptions(UUID userId);
}
