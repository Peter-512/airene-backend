package be.kdg.airene.adapters.in.web.dto;

import be.kdg.airene.domain.data.SensorType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LocationDTO {
	private UUID id;
	private long segment_id;
	private double heavy;
	private double car;
	private double v85;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;
	private double altitude;
	private SensorType sensorType;
	private double distanceKm;
	private LocalDateTime currentLastUpdated;
	private double currentTemperature;
	private double currentUv;
	private double currentGustKph;
	private double currentCo;
	private double currentNo2;
	private double currentO3;
	private double currentSo2;
	private double currentPm2_5;
	private double currentPm10;
	private double currentUsEpaIndex;
	private double currentGbDefraIndex;
	private double p1;
	private double p2;
	private double co_aqi;
	private double no2_aqi;
	private double o3_aqi;
	private double so2_aqi;
	private double pm25_aqi;
	private double pm10_aqi;
	private double aqi;
}
