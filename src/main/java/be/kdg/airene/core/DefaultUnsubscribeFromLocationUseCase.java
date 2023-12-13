package be.kdg.airene.core;

import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.LoadSubscriptionByUserIDAndSubscriptionIDPort;
import be.kdg.airene.ports.in.RemoveSubscriptionPort;
import be.kdg.airene.ports.in.UnsubscribeFromLocationUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultUnsubscribeFromLocationUseCase implements UnsubscribeFromLocationUseCase {

	private final LoadSubscriptionByUserIDAndSubscriptionIDPort loadSubscriptionByUserIDAndSubscriptionIDPort;
	private final RemoveSubscriptionPort removeSubscriptionPort;

	@Override
	public boolean unsubscribeFromLocation(UUID userID, UUID subscriptionId) {
		Optional<Subscription> optSubscription = loadSubscriptionByUserIDAndSubscriptionIDPort.loadSubscriptionByUserIDAndSubscriptionID(userID, subscriptionId);
		if(optSubscription.isEmpty()){
			log.debug("Subscription with id {} not found", subscriptionId);
			throw new IllegalArgumentException("Subscription not found");
		}
		removeSubscriptionPort.removeSubscription(subscriptionId);
		log.debug("Subscription with id {} removed", subscriptionId);
		return true;
	}
}
