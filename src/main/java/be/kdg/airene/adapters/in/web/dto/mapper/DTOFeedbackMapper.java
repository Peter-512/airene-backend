package be.kdg.airene.adapters.in.web.dto.mapper;

import be.kdg.airene.adapters.in.web.dto.SubmitFeedbackDTO;
import be.kdg.airene.domain.feedback.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DTOFeedbackMapper {
	DTOFeedbackMapper INSTANCE = Mappers.getMapper(DTOFeedbackMapper.class);

	Feedback mapToDomain(SubmitFeedbackDTO submitFeedbackDTO);
}
