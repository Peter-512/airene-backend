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
	@JsonProperty ("current.temp_c")
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
	private double co_aqi;
	private double no2_aqi;
	private double o3_aqi;
	private double so2_aqi;
	private double pm25_aqi;
	private double pm10_aqi;
	private double aqi;

	@Override
	public String toString() {
		return """
    				<ul>
					<li>Heavy: %s</li>
					<li>Car: %s</li>
					<li>V85: %s</li>
					<li>Timestamp: %s</li>
					<li>Location: %s</li>
					<li>Altitude: %s</li>
					<li>Sensor type: %s</li>
					<li>Distance km: %s</li>
					<li>temperature: %s</li>
					<li>UV: %s</li>
					<li>gust KPH: %s</li>
					<li>CO: %s</li>
					<li>NO2: %s</li>
					<li>O3: %s</li>
					<li>SO2: %s</li>
					<li>PM2.5: %s</li>
					<li>PM10: %s</li>
					<li>US EPA index: %s</li>
					<li>GB DEFRA index: %s</li>
					<li>P1: %s</li>
					<li>P2: %s</li>
					<li>CO AQI: %s</li>
					<li>NO2 AQI: %s</li>
					<li>O3 AQI: %s</li>
					<li>SO2 AQI: %s</li>
					<li>PM2.5 AQI: %s</li>
					<li>PM10 AQI: %s</li>
					<li>AQI: %s</li>
				</ul>
				""".formatted(heavy, car, v85, timestamp, location, altitude, sensorType, distanceKm, currentTemperature, currentUv, currentGustKph, currentCo, currentNo2, currentO3, currentSo2, currentPm2_5, currentPm10, currentUsEpaIndex, currentGbDefraIndex, p1, p2, co_aqi, no2_aqi, o3_aqi, so2_aqi, pm25_aqi, pm10_aqi, aqi);
	}
}
