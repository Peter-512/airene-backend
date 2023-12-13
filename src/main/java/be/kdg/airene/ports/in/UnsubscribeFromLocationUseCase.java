package be.kdg.airene.ports.in;

import java.util.UUID;

@FunctionalInterface
public interface UnsubscribeFromLocationUseCase {
	boolean unsubscribeFromLocation(UUID userID, UUID subscriptionId);
}
