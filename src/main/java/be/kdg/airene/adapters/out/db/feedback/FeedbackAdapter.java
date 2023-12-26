package be.kdg.airene.adapters.out.db.feedback;

import be.kdg.airene.adapters.out.mapper.FeedbackMapper;
import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.ports.in.FeedbackSavePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FeedbackAdapter implements FeedbackSavePort {
	private final FeedbackRepository feedbackRepository;
	private final FeedbackMapper feedbackMapper = FeedbackMapper.INSTANCE;
	@Override
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackMapper.mapToDomain(feedbackRepository.save(feedbackMapper.mapToJPA(feedback)));
	}
}
