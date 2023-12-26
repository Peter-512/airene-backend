package be.kdg.airene.core;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.GetEntryByIdUseCase;
import be.kdg.airene.ports.in.LoadDataByIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGetEntryById implements GetEntryByIdUseCase {
	private final LoadDataByIdPort loadDataByIdPort;

	@Override
	public Data getEntryById(UUID id) {
		return loadDataByIdPort.loadDataById(id).orElse(null);
	}
}
