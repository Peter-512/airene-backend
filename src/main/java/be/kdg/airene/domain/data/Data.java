package be.kdg.airene.domain.data;

import be.kdg.airene.domain.location.Location;
import be.kdg.airene.domain.location.LocationConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.UUID;

@lombok.Data
public class Data {
	private UUID id;
	private long segment_id;
	private double heavy;
	private double car;
	private double v85;
	@JsonProperty ("timestamp")
	@JsonDeserialize (using = LocalDateTimeConverter.class)
	private LocalDateTime timestamp;
	@JsonDeserialize (using = LocationConverter.class)
	private Location location;
	private double altitude;
	@JsonProperty ("sensor_type")
	@JsonDeserialize (using = SensorTypeConverter.class)
	private SensorType sensorType;
	@JsonProperty ("distance_km")
	private double distanceKm;
	@JsonProperty ("current.last_updated")
	@JsonDeserialize (using = LocalDateTimeNoSecondsConverter.class)
	private LocalDateTime currentLastUpdated;
	@JsonProperty ("current.temperature_c")
	private double currentTemperature;
	@JsonProperty ("current.uv")
	private double currentUv;
	@JsonProperty ("current.gust_kph")
	private double currentGustKph;
	@JsonProperty ("current.air_quality.co")
	private double currentCo;
	@JsonProperty ("current.air_quality.no2")
	private double currentNo2;
	@JsonProperty ("current.air_quality.o3")
	private double currentO3;
	@JsonProperty ("current.air_quality.so2")
	private double currentSo2;
	@JsonProperty ("current.air_quality.pm2_5")
	private double currentPm2_5;
	@JsonProperty ("current.air_quality.pm10")
	private double currentPm10;
	@JsonProperty ("current.air_quality.us-epa-index")
	private double currentUsEpaIndex;
	@JsonProperty ("current.air_quality.gb-defra-index")
	private double currentGbDefraIndex;
	private double p1;
	private double p2;
}
