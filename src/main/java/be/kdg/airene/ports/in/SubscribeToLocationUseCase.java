package be.kdg.airene.ports.in;

import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.user.UserID;

@FunctionalInterface
public interface SubscribeToLocationUseCase {
	void subscribeToLocation(UserID userID, Location location);
}
