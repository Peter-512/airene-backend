package be.kdg.airene.domain.location;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

import java.io.Serializable;

@JsonDeserialize(using = LocationConverter.class)
@Value
public class Location implements Serializable {
	double latitude;
	double longitude;
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
