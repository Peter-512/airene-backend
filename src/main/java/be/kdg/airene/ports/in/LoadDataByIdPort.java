package be.kdg.airene.ports.in;

import be.kdg.airene.domain.data.Data;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadDataByIdPort {
	Optional<Data> loadDataById(UUID predictionId);
}
