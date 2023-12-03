package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.SaveDataEntryBatchesUseCase;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class DefaultSaveDataEntryBatchesUseCase implements SaveDataEntryBatchesUseCase {
	@Override
	public void saveDataEntryBatches(Collection<Data> data) {

	}
}
