package be.kdg.airene.adapters.in.receiver;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.RecieveAnomaliesPort;
import be.kdg.airene.ports.in.RecieveDataBatchesPort;
import be.kdg.airene.ports.in.SaveDataEntryBatchesUseCase;
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class MessageReceiver implements RecieveAnomaliesPort, RecieveDataBatchesPort {
	private final SaveDataEntryBatchesUseCase batchesUseCase;
	private final ObjectMapper objectMapper;
	private final String QUEUE_PREDICTION_NAME = "prediction-cute";
	private final String QUEUE_DATA_NAME = "data-cute";

	private final TypeFactory typeFactory;
	private final JavaType listType;
	private final JavaType hashMapType;
	private final JavaType listMapType;
	@Autowired
	public MessageReceiver(SaveDataEntryBatchesUseCase batchesUseCase, ObjectMapper objectMapper) {
		this.batchesUseCase = batchesUseCase;
		this.objectMapper = objectMapper;
		typeFactory = objectMapper.getTypeFactory();
		listType = typeFactory.constructCollectionType(List.class, Data.class);
		hashMapType = typeFactory.constructMapType(HashMap.class, String.class, Object.class);
		listMapType = typeFactory.constructCollectionType(List.class, hashMapType);
	}

	@ServiceBusListener (destination = QUEUE_PREDICTION_NAME)
	@Override
	public void recieveAnomalies(String json) {
		try {
			LinkedHashMap<String, Object> data = objectMapper.readValue(json, hashMapType);

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
			HashMap<String, Object> data = objectMapper.readValue(json, hashMapType);
			List<Data> dataList = objectMapper.convertValue(data.get("data"), listType);
			log.debug("Data received: {}", dataList);
			//			batchesUseCase.saveDataEntryBatches(dataList);
		}
		catch (JsonProcessingException e) {
			log.error("Couldn't parse json {}", json);
		}
	}
}
