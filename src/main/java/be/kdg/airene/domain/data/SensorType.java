package be.kdg.airene.domain.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.io.Serializable;

@JsonDeserialize(using = SensorTypeConverter.class)
@Value
public class SensorType implements Serializable {
	long sensor_id;
	String name;
	String manufacturer;
}
