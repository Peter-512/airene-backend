package be.kdg.airene.ports.in;

import be.kdg.airene.domain.user.User;

import java.util.List;

@FunctionalInterface
public interface NearestUsersLoadPort {
	List<User> loadUsersSubscribedToNearestAnomaly(double latitude, double longitude);
}
