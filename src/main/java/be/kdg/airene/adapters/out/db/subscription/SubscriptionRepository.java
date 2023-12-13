package be.kdg.airene.adapters.out.db.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionJPA, UUID> {
	List<SubscriptionJPA> findAllByUserId(UUID userId);

	Optional<SubscriptionJPA> findByUserIdAndId(UUID userId, UUID id);
}
