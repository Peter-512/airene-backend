package be.kdg.airene.ports.in;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;

import java.time.LocalDate;
import java.util.List;

public interface GetAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort {
	List<Data> getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKm(LocalDate date, Location location, double radiusKm);
}
