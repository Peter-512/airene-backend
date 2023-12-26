package be.kdg.airene.adapters.out.db.subscription;

import be.kdg.airene.adapters.out.mapper.SubscriptionMapper;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class SubscriptionsAdapter implements UpdateSubscriptionPort, LoadSubscriptionsByUserIdPort, RemoveSubscriptionPort, LoadSubscriptionByUserIDAndSubscriptionIDPort, NearestSubscriptionsLoadPort {

	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionMapper mapper = SubscriptionMapper.INSTANCE;

	@Override
	public Set<Subscription> loadSubscriptionsByUserId(UUID userId, String sort) {
		Sort sortBy = Objects.equals(sort, "desc") ? Sort.by(Sort.Direction.DESC, "createdAt") : Sort.by(Sort.Direction.ASC, "createdAt");
		Set<Subscription> subscriptions = mapper.toDomain(subscriptionRepository.findByUser_Id(userId, sortBy));
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

	@Override
	public Set<Subscription> loadNearestSubscriptions(double latitude, double longitude) {
		return mapper.toDomain(subscriptionRepository.findAllNearestSubscriptionsToLocation(latitude, longitude, 4));
	}
}
