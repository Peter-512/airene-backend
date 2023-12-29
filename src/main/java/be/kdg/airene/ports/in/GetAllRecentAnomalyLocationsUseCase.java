package be.kdg.airene.ports.in;

import be.kdg.airene.domain.data.Data;

import java.time.LocalDateTime;
import java.util.List;

public interface GetAllRecentAnomalyLocationsUseCase {
	List<Data> getAllRecentAnomalyLocations(LocalDateTime timestamp);
}
