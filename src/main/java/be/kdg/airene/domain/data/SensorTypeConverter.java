package be.kdg.airene.domain.data;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SensorTypeConverter extends StdDeserializer<SensorType> {

	ObjectMapper mapper = new ObjectMapper();
	public SensorTypeConverter() {
		this(null);
	}

	public SensorTypeConverter(Class<?> vc) {
		super(vc);
	}

	@Override
	public SensorType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		long id;
		String name;
		String manufacturer;
		try {
			 id = node.get("id").asLong();
			 name = node.get("name").asText();
			 manufacturer = node.get("manufacturer").asText();
		}
		catch (NullPointerException e) {
			String replaced = node.asText().replace("'", "\"");
			node = mapper.readTree(replaced);
			 id = node.get("id").asLong();
			 name = node.get("name").asText();
			 manufacturer = node.get("manufacturer").asText();
			log.debug("SensorTypeConverter: id: {}, name: {}, manufacturer: {}", id, name, manufacturer);
			return new SensorType(id, name, manufacturer);
		}
		return new SensorType(id, name, manufacturer);
	}
}
