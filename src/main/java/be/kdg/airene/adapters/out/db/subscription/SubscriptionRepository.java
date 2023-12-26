package be.kdg.airene.adapters.out.db.subscription;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionJPA, UUID> {
	Set<SubscriptionJPA> findByUser_Id(UUID userId, Sort sort);
	Optional<SubscriptionJPA> findByUserIdAndId(UUID userId, UUID id);

	@Query(value = """
			SELECT DISTINCT s FROM SubscriptionJPA s
			JOIN fetch UserJPA u ON s.user.id = u.id
			WHERE FUNCTION('ACOS', FUNCTION('COS', FUNCTION('RADIANS', :latitude)) * FUNCTION('COS', FUNCTION('RADIANS', s.location.latitude)) *
				  FUNCTION('COS', FUNCTION('RADIANS', s.location.longitude) - FUNCTION('RADIANS', :longitude)) +
				  FUNCTION('SIN', FUNCTION('RADIANS', :latitude)) * FUNCTION('SIN', FUNCTION('RADIANS', s.location.latitude)))
				  * 6371 < :radius
			""")
	Set<SubscriptionJPA> findAllNearestSubscriptionsToLocation(@Param ("latitude") double latitude,
	                                                           @Param ("longitude") double longitude,
	                                                           @Param("radius") double radius);
}
