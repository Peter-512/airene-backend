package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.out.db.prediction.PredictionJPA;
import be.kdg.airene.domain.anomaly.Prediction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PredictionMapper {
	PredictionMapper INSTANCE = Mappers.getMapper(PredictionMapper.class);

	Prediction toDomain(PredictionJPA predictionJPA);
	PredictionJPA toJPA(Prediction prediction);

	Collection<PredictionJPA> toJPA(Collection<Prediction> predictions);
	Collection<Prediction> toDomain(Collection<PredictionJPA> predictionJPAS);
}
