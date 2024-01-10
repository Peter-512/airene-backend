package be.kdg.airene.ports.out;

import be.kdg.airene.domain.data.Data;

import java.time.LocalDateTime;
import java.util.List;

public interface GetAllRecentAnomaliesPort {
	List<Data> getAllRecentAnomalyLocations(LocalDateTime timestamp);
}
