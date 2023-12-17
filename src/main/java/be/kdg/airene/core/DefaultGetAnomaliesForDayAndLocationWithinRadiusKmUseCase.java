package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.GetAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort;
import be.kdg.airene.ports.in.GetAnomaliesForDayAndLocationWithinRadiusKmUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetAnomaliesForDayAndLocationWithinRadiusKmUseCase implements GetAnomaliesForDayAndLocationWithinRadiusKmUseCase {

	private final GetAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort;


	@Override
	public List<Data> getAnomaliesForDayAndLocationWithinRadiusKm(LocalDate date, Location location, double radiusKm) {
		return getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort.getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKm(date, location, radiusKm);
	}
}
