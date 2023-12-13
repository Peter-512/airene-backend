package be.kdg.airene.domain.user;

import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.subscription.Subscription;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static be.kdg.airene.domain.subscription.Subscription.subscribe;

@Getter
@Setter
public class User {
	private UUID id;
	private String name;
	private String email;
	private Set<Subscription> subscriptions = new HashSet<>();

	public Subscription subscribeToLocation(Location location) {
		Subscription subscription = subscribe(this.id, location);
		boolean added = subscriptions.add(subscription);
		if(!added){
			return null;
		}
		return subscription;
	}
}
