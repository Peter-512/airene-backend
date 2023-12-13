package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.SubscriptionRequestDTO;
import be.kdg.airene.domain.location.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper {
	LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
	Location toDomain (SubscriptionRequestDTO subscriptionRequestDTO);
}
