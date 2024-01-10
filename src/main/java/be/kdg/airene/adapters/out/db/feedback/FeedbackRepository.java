package be.kdg.airene.adapters.out.db.feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<FeedbackJPA, Long> {

	List<FeedbackJPA> findByAnomalyId(UUID anomalyId);
}
