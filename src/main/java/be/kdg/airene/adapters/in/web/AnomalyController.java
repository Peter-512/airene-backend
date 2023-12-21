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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

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


	@CacheEvict(value = "anomalyCache", allEntries = true)
	@Scheduled(fixedRateString = "3600") // 1 hour
	public void clearCache() {}

	@PostMapping("/{id}")
	public ResponseEntity<Void> submitFeedback(@RequestBody SubmitFeedbackDTO submitFeedbackDTO, @PathVariable UUID id) {
		submitAnomalyFeedbackUseCase.submitAnomalyFeedback(id,mapper.mapToDomain(submitFeedbackDTO));
		return ResponseEntity.ok().build();
	}
}
