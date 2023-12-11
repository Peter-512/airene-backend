package be.kdg.airene.adapters.out.db.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DataRepository  extends JpaRepository<DataJPA, UUID> {


	@Query("""
			SELECT d
			FROM DataJPA d
			WHERE d.timestamp = (max(d.timestamp))
		""")
	List<DataJPA> findMostRecentData();
}
