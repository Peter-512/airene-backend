package be.kdg.airene.adapters.out.db.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DataRepository  extends JpaRepository<DataJPA, UUID> {
}
