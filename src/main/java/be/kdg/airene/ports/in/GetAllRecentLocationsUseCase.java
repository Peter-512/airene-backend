package be.kdg.airene.ports.in;


import be.kdg.airene.domain.data.Data;

import java.util.List;

@FunctionalInterface
public interface GetAllRecentLocationsUseCase {
	List<Data> getAllRecentLocations();
}
