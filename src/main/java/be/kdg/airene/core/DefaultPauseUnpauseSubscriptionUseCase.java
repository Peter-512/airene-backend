package be.kdg.airene.core;

import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.PauseUnpauseSubscriptionUseCase;
import be.kdg.airene.ports.out.LoadSubscriptionByUserIDAndSubscriptionIDPort;
import be.kdg.airene.ports.out.UpdateSubscriptionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class DefaultPauseUnpauseSubscriptionUseCase implements PauseUnpauseSubscriptionUseCase {

	private final LoadSubscriptionByUserIDAndSubscriptionIDPort loadSubscriptionByUserIDAndSubscriptionIDPort;
	private final UpdateSubscriptionPort updateSubscriptionPort;

	@Override
	public Optional<Subscription> togglePauseSubscription(UUID userId, UUID subscriptionId) {
		Optional<Subscription> subscription = loadSubscriptionByUserIDAndSubscriptionIDPort.loadSubscriptionByUserIDAndSubscriptionID(userId, subscriptionId);
		subscription.ifPresentOrElse(
			sub -> {
				sub.togglePause();
				updateSubscriptionPort.updateSubscription(sub);
				log.debug("Subscription with id {} is now {}", subscriptionId, sub.isPause() ? "paused" : "unpaused");
			},
			() -> log.debug("Subscription with id {} not found", subscriptionId)
		);
		return subscription;
	}
}
