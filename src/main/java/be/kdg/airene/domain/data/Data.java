package be.kdg.airene.domain.data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@lombok.Data
public class Data implements Serializable {
	private UUID id;
	private Map<String, Object> data;
}
