package be.kdg.airene.adapters.out.db.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data", indexes = {
		@Index(name = "idx_data_timestamp", columnList = "timestamp"),
		@Index(name = "idx_data_longitude", columnList = "longitude"),
		@Index(name = "idx_data_latitude", columnList = "latitude"),
})
public class DataJPA {
	@Id
	private UUID id;
	private long segment_id;
	private double heavy;
	private double car;
	private double v85;
	private LocalDateTime timestamp;
	@Embedded
	private LocationJPA location;
	private double altitude;
	@Embedded
	private SensorTypeJPA sensorType;
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
