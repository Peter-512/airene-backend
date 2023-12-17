package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.InfoDataDTO;
import be.kdg.airene.adapters.in.web.dto.LocationDTO;
import be.kdg.airene.adapters.out.db.data.DataJPA;
import be.kdg.airene.adapters.out.db.data.DataJPAInfo;
import be.kdg.airene.domain.data.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DataEntryMapper {
	DataEntryMapper INSTANCE = Mappers.getMapper(DataEntryMapper.class);

	DataJPA mapToJpa(Data data);
	Collection<DataJPA> mapToDataJPA(Collection<Data> data);

	Data mapToDataDomain(DataJPA data);
	List<Data> mapToDataDomain(List<DataJPA> data);

	@Mappings({
			@Mapping(target = "latitude", source = "location.latitude"),
			@Mapping(target = "longitude", source = "location.longitude"),
	})
	LocationDTO mapToDTO(Data data);
	List<LocationDTO> mapToDTO(List<Data> allRecentLocations);

	default InfoDataDTO toDataInfoDTO(List<DataJPAInfo> data){
		InfoDataDTO dataDTO = new InfoDataDTO();
		dataDTO.setData(data);
		return dataDTO;
	}
}
