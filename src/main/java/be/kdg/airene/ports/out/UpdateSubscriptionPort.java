package be.kdg.airene.ports.out;

import be.kdg.airene.domain.subscription.Subscription;

@FunctionalInterface
public interface UpdateSubscriptionPort {
	Subscription updateSubscription(Subscription subscription);
}
