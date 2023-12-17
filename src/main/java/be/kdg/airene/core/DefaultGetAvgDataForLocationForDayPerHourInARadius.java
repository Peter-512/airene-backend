package be.kdg.airene.core;

import be.kdg.airene.adapters.out.db.data.DataJPAInfo;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.GetAvgDataForDayAndLocationWithinRadiusUseCase;
import be.kdg.airene.ports.in.GetAvgForLocationPerDayPerHourWithinRadiusPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultGetAvgDataForLocationForDayPerHourInARadius implements GetAvgDataForDayAndLocationWithinRadiusUseCase {

	private final GetAvgForLocationPerDayPerHourWithinRadiusPort getAvgForLocationPerDayPerHourPort;
	@Override
	public List<DataJPAInfo> getAvgMedianTotalDataForDayAndLocationWithinRadius(LocalDate date, Location location, double radiusKm) {
		return getAvgForLocationPerDayPerHourPort.getAvgForLocationPerDayPerHourWithinRadius(
			date,
			location,
			radiusKm
		);
	}
}
