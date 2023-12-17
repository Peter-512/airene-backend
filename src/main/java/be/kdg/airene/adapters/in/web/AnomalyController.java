package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.SubmitFeedbackDTO;
import be.kdg.airene.adapters.out.mapper.FeedbackMapper;
import be.kdg.airene.ports.in.SubmitAnomalyFeedbackUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

	private final SubmitAnomalyFeedbackUseCase submitAnomalyFeedbackUseCase;
	private final FeedbackMapper mapper = FeedbackMapper.INSTANCE;


	@Cacheable (value = "dataCache", key = "{#date, #latitude, #longitude, #radius}")
	@GetMapping
	public ResponseEntity<?>
	getAvgMedianTotalDataForDayAndLocationWithinRadiusKm
			(@NotNull @RequestParam ("date") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date,
			 @Valid @RequestParam("latitude") double latitude,
			 @Valid @RequestParam("longitude") double longitude,
			 @RequestParam("radius") double radius) {
		var data = getAnomaliesForDayAndLocationWithinRadiusKm(date, latitude, longitude, radius);
		return ResponseEntity.ok(
				new HashMap<String, List<?>>(){{
					put("avg", avg.getAverage());
					put("total", total.getTotal());
				}}
		);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> submitFeedback(@RequestBody SubmitFeedbackDTO submitFeedbackDTO, @PathVariable UUID id) {
		submitAnomalyFeedbackUseCase.submitAnomalyFeedback(id,mapper.mapToDomain(submitFeedbackDTO));
		return ResponseEntity.ok().build();
	}
}
