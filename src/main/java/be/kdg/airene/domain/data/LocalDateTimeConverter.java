package be.kdg.airene.domain.data;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalDateTimeConverter extends StdDeserializer<LocalDateTime> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public LocalDateTimeConverter() {
		this(null);
	}

	public LocalDateTimeConverter(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws java.io.IOException {
		JsonNode node = p.getCodec().readTree(p);
		try {
			return LocalDateTime.parse(node.asText(), FORMATTER);
		} catch (Exception e) {
			log.debug("Error parsing date");
		}
		Instant instant = Instant.ofEpochMilli(node.asLong());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
}
