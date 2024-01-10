package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.GetAllRecentAnomalyLocationsUseCase;
import be.kdg.airene.ports.out.GetAllRecentAnomaliesPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetAllRecentAnomalyLocationsUseCase implements GetAllRecentAnomalyLocationsUseCase {

	private final GetAllRecentAnomaliesPort getAllRecentAnomaliesPort;

	@Override
	public List<Data> getAllRecentAnomalyLocations(LocalDateTime timestamp) {
		return getAllRecentAnomaliesPort.getAllRecentAnomalyLocations(timestamp);
	}
}
