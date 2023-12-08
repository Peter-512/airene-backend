package be.kdg.airene.adapters.out.db.data;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable public class SensorTypeJPA {
	private long sensor_id;
	private String name;
	private String manufacturer;
}
