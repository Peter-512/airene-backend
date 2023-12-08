package be.kdg.airene.adapters.in.receiver;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.ReceiveAnomaliesPort;
import be.kdg.airene.ports.in.ReceiveDataBatchesPort;
import be.kdg.airene.ports.in.SaveDataEntryBatchesUseCase;
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class MessageReceiver implements ReceiveAnomaliesPort, ReceiveDataBatchesPort {
	private final SaveDataEntryBatchesUseCase batchesUseCase;
	private final ObjectMapper objectMapper;
	private final String QUEUE_PREDICTION_NAME = "prediction-cute";
	private final String QUEUE_DATA_NAME = "clean-data-cute";

	private final TypeFactory typeFactory;
	private final JavaType listDataType;
	private final JavaType listLocationType;
	@Autowired
	public MessageReceiver(SaveDataEntryBatchesUseCase batchesUseCase, ObjectMapper objectMapper) {
		this.batchesUseCase = batchesUseCase;
		this.objectMapper = objectMapper;
		typeFactory = objectMapper.getTypeFactory();
		listDataType = typeFactory.constructCollectionType(List.class, Data.class);
		listLocationType = typeFactory.constructCollectionType(List.class, Location.class);
	}

	@ServiceBusListener (destination = QUEUE_PREDICTION_NAME)
	@Override
	public void recieveAnomalies(String json) {
		try {
			LinkedHashMap<String, Object> data = objectMapper.readValue(json, listDataType);

		}
		catch (JsonProcessingException e) {
			log.error("Couldn't parse json {}", json);
		}

	}

	@ServiceBusListener (destination = QUEUE_DATA_NAME)
	@Override
	public void recieveDataBatches(String json) {
		try {
			log.debug("Message received: {}", json);
			List<Data> data = objectMapper.readValue(json, listDataType);
			List<Location> locations = objectMapper.readValue(json, listLocationType);
			for (int i = 0; i < data.size(); i++) {
				data.get(i).setLocation(locations.get(i));
			}
			batchesUseCase.saveDataEntryBatches(data);
		}
		catch (JsonProcessingException e) {
			log.error("Couldn't parse json as a list womp womp");
			try {
				Data data = objectMapper.readValue(json, Data.class);
				Location location = objectMapper.readValue(json, Location.class);
				data.setLocation(location);
				batchesUseCase.saveDataEntryBatches(List.of(data));
			}
			catch (JsonProcessingException error) {
				log.error("Couldn't parse json as a hashmap womp womp");
			}
		}
	}
}
