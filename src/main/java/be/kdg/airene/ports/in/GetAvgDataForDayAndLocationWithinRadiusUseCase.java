package be.kdg.airene.ports.in;

import be.kdg.airene.adapters.out.db.data.DataJPAAverageInfo;
import be.kdg.airene.domain.location.Location;

import java.time.LocalDate;
import java.util.List;

public interface GetAvgDataForDayAndLocationWithinRadiusUseCase {
	List<DataJPAAverageInfo> getAvgMedianTotalDataForDayAndLocationWithinRadius(LocalDate date, Location location, double radiusKm);
}
