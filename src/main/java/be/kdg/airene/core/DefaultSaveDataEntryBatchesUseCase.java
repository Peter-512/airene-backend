package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.DataEntryBatchSaverPort;
import be.kdg.airene.ports.in.SaveDataEntryBatchesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class DefaultSaveDataEntryBatchesUseCase implements SaveDataEntryBatchesUseCase {

	private final DataEntryBatchSaverPort dataEntryBatchSaverPort;

	@Override
	public void saveDataEntryBatches(Collection<Data> data) {
		dataEntryBatchSaverPort.saveDataEntryBatches(data);
	}
}
