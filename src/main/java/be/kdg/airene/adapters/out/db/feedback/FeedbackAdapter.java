package be.kdg.airene.adapters.out.db.feedback;

import be.kdg.airene.adapters.out.mapper.FeedbackMapper;
import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.ports.out.FeedbackSavePort;
import be.kdg.airene.ports.out.LoadFeedbackByAnomalyIdPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class FeedbackAdapter implements FeedbackSavePort, LoadFeedbackByAnomalyIdPort {
	private final FeedbackRepository feedbackRepository;
	private final FeedbackMapper feedbackMapper = FeedbackMapper.INSTANCE;
	@Override
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackMapper.mapToDomain(feedbackRepository.save(feedbackMapper.mapToJPA(feedback)));
	}
	@Override
	public List<Feedback> loadFeedbackByAnomalyId(UUID anomalyId) {
		return feedbackMapper.mapToDomain(feedbackRepository.findByAnomalyId(anomalyId));
	}
}
