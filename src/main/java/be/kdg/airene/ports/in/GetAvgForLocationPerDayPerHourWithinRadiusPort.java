package be.kdg.airene.ports.in;

import be.kdg.airene.adapters.out.db.data.DataJPAAverageInfo;
import be.kdg.airene.domain.location.Location;

import java.time.LocalDate;
import java.util.List;

@FunctionalInterface
public interface GetAvgForLocationPerDayPerHourWithinRadiusPort {
	List<DataJPAAverageInfo> getAvgForLocationPerDayPerHourWithinRadius(LocalDate date, Location location, double radiusKm);
}
