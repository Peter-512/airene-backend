package be.kdg.airene.adapters.out.db.anomaly;

import be.kdg.airene.adapters.out.db.data.DataRepository;
import be.kdg.airene.adapters.out.mapper.AnomalyMapper;
import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.ports.out.AnomalyLoadPort;
import be.kdg.airene.ports.out.AnomalySavePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AnomalyAdapter implements AnomalySavePort, AnomalyLoadPort {
	private final AnomalyRepository anomalyRepository;
	private final DataRepository dataRepository;
	private final AnomalyMapper anomalyMapper = AnomalyMapper.INSTANCE;
	@Override
	public Optional<Anomaly> loadAnomaly(UUID id) {
		return anomalyRepository.findById(id).map(anomalyMapper::toDomain);
	}

	@Override
	public Anomaly saveAnomaly(Anomaly anomalyDetection) {
		return anomalyMapper.toDomain(anomalyRepository.save(anomalyMapper.toJPA(anomalyDetection)));
	}
}
