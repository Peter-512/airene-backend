package be.kdg.airene.domain.location;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

@Value
public class Location {
	double latitude;
	double longitude;
}
