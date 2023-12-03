package be.kdg.airene.domain.subscription;

import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription {

	private SubscriptionID subscriptionID;
	private User user;
	private Location location;
}
