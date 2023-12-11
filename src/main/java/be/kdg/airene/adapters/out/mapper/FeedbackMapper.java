package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.SubmitFeedbackDTO;
import be.kdg.airene.domain.feedback.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
	FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

	Feedback mapToDomain(SubmitFeedbackDTO submitFeedbackDTO);
}
