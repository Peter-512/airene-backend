package be.kdg.airene.adapters.out.db.data;

import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DataEntryAdapter implements DataEntryBatchSaverPort, GetAllRecentLocationsPort, LoadDataByIdPort, GetAvgForLocationPerDayPerHourWithinRadiusPort, GetTotalForLocationPerDayPerHourWithinRadiusPort, GetAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKmPort {

	private final DataRepository dataRepository;
	private final DataEntryMapper mapper = DataEntryMapper.INSTANCE;

	@Override
	public void saveDataEntryBatches(Collection<Data> data) {
		dataRepository.saveAll(
			mapper.mapToDataJPA(data)
		);
	}

	@Override
	public List<Data> getAllRecentLocations() {
		return mapper.mapToDataDomain(
			dataRepository.findMostRecentData()
		);
	}

	@Override
	public Optional<Data> loadDataById(UUID dataId) {
		return dataRepository.findById(dataId).map(mapper::mapToDataDomain);
	}

	@Override
	public List<DataJPAInfo> getAvgForLocationPerDayPerHourWithinRadius(LocalDate date, Location location, double radiusKm) {
		return dataRepository.getAverageValuesPerHourAscendingForDayInARadiusOfLocation(
			date,
			location.getLatitude(),
			location.getLongitude(),
			radiusKm
		);
	}
	@Override
	public List<DataJPAInfo> getTotalForLocationPerDayPerHourWithinRadius(LocalDate date, Location location, double radiusKm) {
		return dataRepository.getTotalValuesPerHourAscendingForDayInARadiusOfLocation(
			date,
			location.getLatitude(),
			location.getLongitude(),
			radiusKm
		);
	}

	@Override
	public List<Data> getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKm(LocalDate date, Location location, double radiusKm) {
		return mapper.mapToDataDomain(
			dataRepository.getAllDataThatAreAnomaliesForDayAndLocationWithinRadiusKm(
				date,
				location.getLatitude(),
				location.getLongitude(),
				radiusKm
			)
		);
	}
}
