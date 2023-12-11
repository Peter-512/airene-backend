package be.kdg.airene.domain.anomaly;

import be.kdg.airene.domain.data.LocalDateTimeConverter;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.location.LocationConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Prediction implements Serializable{
	private UUID id;
	@JsonDeserialize (using = LocalDateTimeConverter.class)
	private LocalDateTime timestamp;
	@JsonUnwrapped
	@JsonDeserialize (using = LocationConverter.class)
	private Location location;
	@JsonProperty("data_id")
	private UUID dataId;
	@JsonProperty("average_regressor_prediction")
	private double averageRegression;
	@JsonProperty("anomaly")
	private boolean isAnomaly;
}
