package be.kdg.airene.ports.in;

import be.kdg.airene.domain.subscription.Subscription;

@FunctionalInterface
public interface UpdateSubscriptionPort {
	Subscription updateSubscription(Subscription subscription);
}
