package be.kdg.airene.adapters.out.db.prediction;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PredictionRepository extends JpaRepository<PredictionJPA, UUID> {
}
