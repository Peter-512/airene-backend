package be.kdg.airene.adapters.in.receiver;

import be.kdg.airene.domain.anomaly.Prediction;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.location.Location;
import be.kdg.airene.ports.in.*;
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MessageReceiver implements ReceivePredictionsPort, ReceiveDataBatchesPort {
	private final SaveDataEntryBatchesUseCase batchesUseCase;
	private final SavePredictionsUseCase predictionsUseCase;
	private final AnomalyDetectedUseCase anomalyDetectedUseCase;
	private final ObjectMapper objectMapper;
	private final String QUEUE_PREDICTION_NAME = "prediction-cute";
	private final String QUEUE_DATA_NAME = "clean-data-cute";
	private final JavaType listDataType;
	private final JavaType listPredictionType;
	private final JavaType listLocationType;
	@Autowired
	public MessageReceiver(SaveDataEntryBatchesUseCase batchesUseCase, SavePredictionsUseCase predictionsUseCase, AnomalyDetectedUseCase anomalyDetectedUseCase, ObjectMapper objectMapper) {
		this.anomalyDetectedUseCase = anomalyDetectedUseCase;
		this.batchesUseCase = batchesUseCase;
		this.objectMapper = objectMapper;
		this.predictionsUseCase = predictionsUseCase;
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		listDataType = typeFactory.constructCollectionType(List.class, Data.class);
		listLocationType = typeFactory.constructCollectionType(List.class, Location.class);
		listPredictionType = typeFactory.constructCollectionType(List.class, Prediction.class);
	}

	@ServiceBusListener (destination = QUEUE_PREDICTION_NAME)
	@Override
	public void recievePredictions(String json) {
		try {
			List<Prediction> predictionList = objectMapper.readValue(json, listPredictionType);
			List<Location> locations = objectMapper.readValue(json, listLocationType);
			for (int i = 0; i < predictionList.size(); i++) {
				predictionList.get(i).setLocation(locations.get(i));
			}
			predictionsUseCase.savePredictions(predictionList);
			predictionList.parallelStream()
					.forEach(anomalyDetectedUseCase::anomalyDetected);
		}
		catch (JsonProcessingException e) {
			try {
				Prediction prediction = objectMapper.readValue(json, Prediction.class);
				Location location = objectMapper.readValue(json, Location.class);
				prediction.setLocation(location);
				predictionsUseCase.savePredictions(List.of(prediction));
				anomalyDetectedUseCase.anomalyDetected(prediction);
			}
			catch (JsonProcessingException error) {
				log.error("Couldn't parse prediction womp womp");
			}
		}
	}

	@ServiceBusListener (destination = QUEUE_DATA_NAME)
	@Override
	public void recieveDataBatches(String json) {
		try {
			List<Data> data = objectMapper.readValue(json, listDataType);
			List<Location> locations = objectMapper.readValue(json, listLocationType);
			for (int i = 0; i < data.size(); i++) {
				data.get(i).setLocation(locations.get(i));
			}
			batchesUseCase.saveDataEntryBatches(data);
		}
		catch (JsonProcessingException e) {
			try {
				Data data = objectMapper.readValue(json, Data.class);
				Location location = objectMapper.readValue(json, Location.class);
				data.setLocation(location);
				batchesUseCase.saveDataEntryBatches(List.of(data));
			}
			catch (JsonProcessingException error) {
				log.error("Couldn't parse data womp womp");
			}
		}
	}
}
