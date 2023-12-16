package be.kdg.airene.core;

import be.kdg.airene.adapters.out.db.data.DataJPASumInfo;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.GetTotalDataForLocationPerHourInARadiusUseCase;
import be.kdg.airene.ports.in.GetTotalForLocationPerDayPerHourWithinRadiusPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetTotalDataForLocationPerHourInARadiusUseCase implements GetTotalDataForLocationPerHourInARadiusUseCase {

	private final GetTotalForLocationPerDayPerHourWithinRadiusPort getTotalForLocationPerDayPerHourWithinRadiusPort;

	@Override
	public List<DataJPASumInfo> getTotalDataForLocationPerHourInARadius(LocalDate date, Location location, double radiusKm) {
		return getTotalForLocationPerDayPerHourWithinRadiusPort.getTotalForLocationPerDayPerHourWithinRadius(
			date,
			location,
			radiusKm
		);
	}
}
