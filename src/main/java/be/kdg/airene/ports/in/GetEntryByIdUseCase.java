package be.kdg.airene.ports.in;

import be.kdg.airene.domain.data.Data;

import java.util.UUID;

@FunctionalInterface
public interface GetEntryByIdUseCase {
	Data getEntryById(UUID id);
}
