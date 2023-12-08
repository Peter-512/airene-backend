package be.kdg.airene.adapters.out.db.data;

import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.DataEntryBatchSaverPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class DataEntryAdapter implements DataEntryBatchSaverPort {

	private final DataRepository dataRepository;
	private final DataEntryMapper mapper = DataEntryMapper.INSTANCE;

	@Override
	public void saveDataEntryBatches(Collection<Data> data) {
		dataRepository.saveAll(
			mapper.mapToDataJPA(data)
		);
	}
}
