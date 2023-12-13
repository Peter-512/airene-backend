package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.SubscriptionDTO;
import be.kdg.airene.adapters.out.db.subscription.SubscriptionJPA;
import be.kdg.airene.domain.subscription.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
	SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);



	 SubscriptionJPA toJPA(Subscription subscription);
	 Subscription toDomain(SubscriptionJPA subscriptionJPA);


	 @Mapping(source = "location.latitude", target = "latitude")
	 @Mapping(source = "location.longitude", target = "longitude")
	 SubscriptionDTO toDTO(Subscription subscription);
	 List<SubscriptionJPA> toJPA(Collection<Subscription> subscriptions);
	 List<Subscription> toDomain(Collection<SubscriptionJPA> subscriptionJPAS);
	 List<SubscriptionDTO> toDTO(Collection<Subscription> subscriptions);
}
