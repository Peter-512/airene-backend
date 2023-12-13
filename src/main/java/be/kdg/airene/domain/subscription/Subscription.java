package be.kdg.airene.domain.subscription;

import be.kdg.airene.domain.location.SubscribedLocation;
import be.kdg.airene.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Subscription {
	private UUID id;
	private User user;
	private SubscribedLocation location;

	public static Subscription subscribe(User user, SubscribedLocation location) {
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setLocation(location);
		return subscription;
	}
}
