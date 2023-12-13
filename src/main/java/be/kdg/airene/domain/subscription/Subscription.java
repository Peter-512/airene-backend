package be.kdg.airene.domain.subscription;

import be.kdg.airene.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Subscription {
	private UUID id;
	private UUID userId;
	private Location location;

	public static Subscription subscribe(UUID userId, Location location) {
		Subscription subscription = new Subscription();
		subscription.setUserId(userId);
		subscription.setLocation(location);
		return subscription;
	}
}
