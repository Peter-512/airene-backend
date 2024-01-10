package be.kdg.airene.ports.out;

import java.util.UUID;

public interface RemoveSubscriptionPort {
	void removeSubscription(UUID subscriptionId);
}
