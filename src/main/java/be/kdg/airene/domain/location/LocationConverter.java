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
		log.debug("Node: {}", node);
		double latitude = node.get("latitude").asDouble();
		double longitude = node.get("longitude").asDouble();
		return new Location(latitude, longitude);
	}
}
