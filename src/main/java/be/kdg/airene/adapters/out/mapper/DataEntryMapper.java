package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.out.db.data.DataJPA;
import be.kdg.airene.domain.data.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface DataEntryMapper {
	DataEntryMapper INSTANCE = Mappers.getMapper(DataEntryMapper.class);

	DataJPA mapToJpa(Data data);
	Collection<DataJPA> mapToDataJPA(Collection<Data> data);
}
