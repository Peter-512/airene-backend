package be.kdg.airene.adapters.out.db.data;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "data")
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
}
