package be.kdg.airene.domain.data;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalDateTimeNoSecondsConverter extends StdDeserializer<LocalDateTime> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public LocalDateTimeNoSecondsConverter() {
		this(null);
	}

	public LocalDateTimeNoSecondsConverter(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws java.io.IOException, com.fasterxml.jackson.core.JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		return LocalDateTime.parse(node.asText(), FORMATTER);
	}
}
