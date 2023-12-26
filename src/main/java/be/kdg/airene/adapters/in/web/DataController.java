package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.LocationDTO;
import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.domain.data.Data;
import be.kdg.airene.ports.in.GetAvgDataForDayAndLocationWithinRadiusUseCase;
import be.kdg.airene.ports.in.GetEntryByIdUseCase;
import be.kdg.airene.ports.in.GetTotalDataForLocationPerHourInARadiusUseCase;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/data")
@AllArgsConstructor
@Slf4j
public class DataController {

	private CacheManager cacheManager;
	private final LocationMapper locationMapper = LocationMapper.INSTANCE;
	private final DataEntryMapper dataEntryMapper = DataEntryMapper.INSTANCE;

	private final GetAvgDataForDayAndLocationWithinRadiusUseCase getAvgDataForDayAndLocationWithinRadiusUseCase;
	private final GetTotalDataForLocationPerHourInARadiusUseCase getTotalDataForLocationPerHourInARadiusUseCase;
	private final GetEntryByIdUseCase getEntryByIdUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<LocationDTO> getEntryById(@PathVariable UUID id) {
		Data entryById = getEntryByIdUseCase.getEntryById(id);
		if (entryById == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(
			dataEntryMapper.mapToDTO(entryById)
		);
	}


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

	@Scheduled (fixedRateString = "1", timeUnit = TimeUnit.HOURS)
	public void clearCache() {
		LocalDate currentDate = LocalDate.now();
		Cache cache = cacheManager.getCache("dataCache");
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
}
