package be.kdg.airene.ports.in;

import java.util.UUID;

public interface RemoveSubscriptionPort {
	void removeSubscription(UUID subscriptionId);
}
