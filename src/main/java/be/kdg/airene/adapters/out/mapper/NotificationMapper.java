package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.NotificationDTO;
import be.kdg.airene.adapters.out.db.feedback.FeedbackJPA;
import be.kdg.airene.adapters.out.db.notification.NotificationJPA;
import be.kdg.airene.domain.feedback.Feedback;
import be.kdg.airene.domain.notification.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

	NotificationJPA toJPA(Notification notification);
	Notification toDomain(NotificationJPA notificationJPA);
	@Mapping(target = "anomaly.feedback", source = "anomaly.feedback")
	NotificationDTO toDTO(Notification notification);
	List<Notification> toDomain(Collection<NotificationJPA> notificationJPAS);
	List<NotificationDTO> toDTO(Collection<Notification> userSubscriptions);
	Feedback mapToDomain(FeedbackJPA feedbackJPA);
}
