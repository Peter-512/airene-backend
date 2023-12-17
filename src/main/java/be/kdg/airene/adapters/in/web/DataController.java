package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.ports.in.GetAvgDataForDayAndLocationWithinRadiusUseCase;
import be.kdg.airene.ports.in.GetTotalDataForLocationPerHourInARadiusUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@AllArgsConstructor
@Slf4j
public class DataController {

	private final LocationMapper locationMapper = LocationMapper.INSTANCE;
	private final DataEntryMapper dataEntryMapper = DataEntryMapper.INSTANCE;

	private final GetAvgDataForDayAndLocationWithinRadiusUseCase getAvgDataForDayAndLocationWithinRadiusUseCase;
	private final GetTotalDataForLocationPerHourInARadiusUseCase getTotalDataForLocationPerHourInARadiusUseCase;

	@Cacheable (value = "dataCache", key = "{#date, #latitude, #longitude, #radius}")
	@GetMapping
	public ResponseEntity<?>
	getAvgMedianTotalDataForDayAndLocationWithinRadiusKm
			(@NotNull @RequestParam ("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
	          @Valid @RequestParam("latitude") double latitude,
	          @Valid @RequestParam("longitude") double longitude,
	          @RequestParam("radius") double radius) {
		var avg = dataEntryMapper.toDataInfoDTO(getAvgDataForDayAndLocationWithinRadiusUseCase.getAvgMedianTotalDataForDayAndLocationWithinRadius(
			date,
			locationMapper.toLocation(latitude, longitude),
			radius
		));
		var total = dataEntryMapper.toDataInfoDTO(getTotalDataForLocationPerHourInARadiusUseCase.getTotalDataForLocationPerHourInARadius(
			date,
			locationMapper.toLocation(latitude, longitude),
			radius
		));
		return ResponseEntity.ok(
			new HashMap<String, List<?>>(){{
				put("avg", avg.getData());
				put("total", total.getData());
			}}
		);
	}
}
