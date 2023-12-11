package be.kdg.airene.domain.location;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LocationConverter  extends StdDeserializer<Location> {
	public LocationConverter() {
		this(null);
	}

	public LocationConverter(Class<?> vc) {
		super(vc);
	}

	@Override
	public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		double lat;
		double lon;
		lat = node.get("latitude").asDouble();
		lon = node.get("longitude").asDouble();
		return new Location(lat, lon);
	}
}
