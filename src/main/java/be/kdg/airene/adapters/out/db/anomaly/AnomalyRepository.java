package be.kdg.airene.adapters.out.db.anomaly;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnomalyRepository extends JpaRepository<AnomalyJPA, UUID> {

}
