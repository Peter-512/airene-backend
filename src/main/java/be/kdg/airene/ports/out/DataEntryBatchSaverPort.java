package be.kdg.airene.ports.out;

import be.kdg.airene.domain.data.Data;

import java.util.Collection;

@FunctionalInterface
public interface DataEntryBatchSaverPort {
	void saveDataEntryBatches(Collection<Data> data);
}
