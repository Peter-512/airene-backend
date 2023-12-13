package be.kdg.airene.core;

import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.in.LoadUserPort;
import be.kdg.airene.ports.in.SubscribeToLocationUseCase;
import be.kdg.airene.ports.in.UpdateSubscriptionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultSubscribeToLocationUseCase implements SubscribeToLocationUseCase {

	private final LoadUserPort loadUserPort;
	private final UpdateSubscriptionPort updateSubscriptionPort;

	@Override
	public Subscription subscribeToLocation(UUID userID, Location location) {
		Optional<User> optUser = loadUserPort.loadUser(userID);
		if(optUser.isEmpty()){
			log.debug("User with id {} not found", userID);
			throw new IllegalArgumentException("User not found");
		}
		User user = optUser.get();
		Subscription subscribed = user.subscribeToLocation(location);
		if(subscribed == null){
			log.debug("User with id {} already subscribed to location {}", userID, location);
		}
		Subscription subscription = updateSubscriptionPort.updateSubscription(subscribed);
		log.debug("User with id {} subscribed to location {}", userID, location);
		return subscription;
	}
}
