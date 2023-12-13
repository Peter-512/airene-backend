package be.kdg.airene.adapters.out.db.data;

import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.DataEntryBatchSaverPort;
import be.kdg.airene.ports.in.GetAllRecentLocationsPort;
import be.kdg.airene.ports.in.LoadDataByIdPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DataEntryAdapter implements DataEntryBatchSaverPort, GetAllRecentLocationsPort, LoadDataByIdPort {

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
}
