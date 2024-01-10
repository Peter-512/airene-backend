package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.adapters.out.db.data.LocationJPA;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AnomalyDTO {
	private UUID id;
	private LocalDateTime timestamp;
	private LocationJPA location;
	private UUID dataId;
	private double averageRegression;
	private List<FeedbackDTO> feedback;
}
