package be.kdg.airene.adapters.out.db.subscription;

import be.kdg.airene.adapters.out.mapper.SubscriptionMapper;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.LoadSubscriptionByUserIDAndSubscriptionIDPort;
import be.kdg.airene.ports.in.LoadSubscriptionsByUserIdPort;
import be.kdg.airene.ports.in.RemoveSubscriptionPort;
import be.kdg.airene.ports.in.UpdateSubscriptionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class SubscriptionsAdapter implements UpdateSubscriptionPort, LoadSubscriptionsByUserIdPort, RemoveSubscriptionPort, LoadSubscriptionByUserIDAndSubscriptionIDPort {

	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionMapper mapper = SubscriptionMapper.INSTANCE;

	@Override
	public List<Subscription> loadSubscriptionsByUserId(UUID userId) {
		List<Subscription> subscriptions = mapper.toDomain(subscriptionRepository.findAllByUserId(userId));
		log.debug("Loaded {} subscriptions for user with id {}", subscriptions.size(), userId);
		return subscriptions;
	}

	@Override
	public Subscription updateSubscription(Subscription subscription) {
		return mapper.toDomain(subscriptionRepository.save(mapper.toJPA(subscription)));
	}

	@Override
	public void removeSubscription(UUID subscriptionId) {
		subscriptionRepository.deleteById(subscriptionId);
		log.debug("Removed subscription with id {}", subscriptionId);
	}

	@Override
	public Optional<Subscription> loadSubscriptionByUserIDAndSubscriptionID(UUID userID, UUID subscriptionID) {
		return subscriptionRepository.findByUserIdAndId(userID, subscriptionID).map(mapper::toDomain);
	}
}
