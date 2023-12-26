package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.SubmitFeedbackDTO;
import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.adapters.out.mapper.FeedbackMapper;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.ports.in.GetAnomaliesForDayAndLocationWithinRadiusKmUseCase;
import be.kdg.airene.ports.in.SubmitAnomalyFeedbackUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {
	private CacheManager cacheManager;
	private final SubmitAnomalyFeedbackUseCase submitAnomalyFeedbackUseCase;
	private final GetAnomaliesForDayAndLocationWithinRadiusKmUseCase anomalyUseCase;
	private final FeedbackMapper mapper = FeedbackMapper.INSTANCE;
	private final DataEntryMapper dataEntryMapper = DataEntryMapper.INSTANCE;
	private final LocationMapper locationMapper = LocationMapper.INSTANCE;

	@Cacheable (value = "anomalyCache", key = "{#date, #latitude, #longitude, #radius}")
	@GetMapping
	public ResponseEntity<?>
	getAvgMedianTotalDataForDayAndLocationWithinRadiusKm
			(@NotNull @RequestParam ("date") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date,
			 @Valid @RequestParam("latitude") double latitude,
			 @Valid @RequestParam("longitude") double longitude,
			 @RequestParam("radius") double radius) {
		var data = anomalyUseCase.getAnomaliesForDayAndLocationWithinRadiusKm(date, locationMapper.toLocation(latitude, longitude), radius);
		return ResponseEntity.ok(
				dataEntryMapper.mapToDTO(data)
		);
	}


	@Scheduled (fixedRateString = "1", timeUnit = TimeUnit.HOURS)
	public void clearCache() {
		LocalDate currentDate = LocalDate.now();
		Cache cache = cacheManager.getCache("anomalyCache");
		if (cache == null) return;
		ConcurrentHashMap<?, ?> map = (ConcurrentHashMap<?, ?>) cache.getNativeCache();
		ConcurrentHashMap.KeySetView<?, ?> keySet = map.keySet();
		log.debug("keySet: " + keySet);
		if (keySet == null || keySet.stream().toList().isEmpty()) return;
		keySet.forEach(key -> {
			var date = (LocalDate) ((List<Object>) key).getFirst();
			if (!date.isBefore(currentDate)) {
				log.debug("evicting key: " + key);
				cache.evict(key);
			}
		});
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> submitFeedback(@RequestBody SubmitFeedbackDTO submitFeedbackDTO, @PathVariable UUID id) {
		submitAnomalyFeedbackUseCase.submitAnomalyFeedback(id,mapper.mapToDomain(submitFeedbackDTO));
		return ResponseEntity.ok().build();
	}
}
