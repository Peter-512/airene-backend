package be.kdg.airene.adapters.out.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserJPA, UUID> {


//	@Query(value = """
//           SELECT u FROM UserJPA u
//           JOIN u.
//  			""")
//	List<User> findAllByNearestLocationLatitudeAndNearestLocationLongitude(double latitude, double longitude, double radiusInMeters);
}
