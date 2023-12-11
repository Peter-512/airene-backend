package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.out.db.anomaly.AnomalyJPA;
import be.kdg.airene.domain.anomaly.Anomaly;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnomalyMapper {
	AnomalyMapper INSTANCE = Mappers.getMapper(AnomalyMapper.class);

	AnomalyJPA toJPA(Anomaly anomaly);
	Anomaly toDomain(AnomalyJPA anomalyJPA);
}
