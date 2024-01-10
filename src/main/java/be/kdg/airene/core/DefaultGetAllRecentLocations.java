package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.GetAllRecentLocationsUseCase;
import be.kdg.airene.ports.out.GetAllRecentLocationsPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultGetAllRecentLocations implements GetAllRecentLocationsUseCase {

	private final GetAllRecentLocationsPort getAllRecentLocationsPort;


	@Override
	public List<Data> getAllRecentLocations() {
		return getAllRecentLocationsPort.getAllRecentLocations();
	}
}
