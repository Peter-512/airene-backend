package be.kdg.airene.adapters.out.db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserJPA, UUID> {

	@Query(value = """
            SELECT DISTINCT u FROM UserJPA u
            JOIN SubscriptionJPA sl ON u.id = sl.userId
            WHERE FUNCTION('ACOS', FUNCTION('COS', FUNCTION('RADIANS', :latitude)) * FUNCTION('COS', FUNCTION('RADIANS', sl.location.latitude)) *
                  FUNCTION('COS', FUNCTION('RADIANS', sl.location.longitude) - FUNCTION('RADIANS', :longitude)) +
                  FUNCTION('SIN', FUNCTION('RADIANS', :latitude)) * FUNCTION('SIN', FUNCTION('RADIANS', sl.location.latitude)))
                  * 6371 < :radius
            """)
	List<UserJPA> findAllByNearestLocationLatitudeAndNearestLocationLongitude(
			@Param("latitude") double latitude,
			@Param("longitude") double longitude,
			@Param ("radius") double radius
	);
}
