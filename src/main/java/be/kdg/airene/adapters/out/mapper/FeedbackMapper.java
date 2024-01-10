package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.FeedbackDTO;
import be.kdg.airene.adapters.in.web.dto.SubmitFeedbackDTO;
import be.kdg.airene.adapters.out.db.feedback.FeedbackJPA;
import be.kdg.airene.domain.feedback.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
	FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

	Feedback mapToDomain(SubmitFeedbackDTO submitFeedbackDTO);
	Feedback mapToDomain(FeedbackJPA feedbackJPA);
	FeedbackJPA mapToJPA(Feedback feedback);
	FeedbackDTO mapToDTO(Feedback feedback);
	List<FeedbackDTO> mapToDTO(List<Feedback> feedbacks);

	List<Feedback> mapToDomain(List<FeedbackJPA> byAnomalyId);
}
