package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.LocationDTO;
import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.ports.in.GetAllRecentLocationsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
@AllArgsConstructor
public class LocationController {

	private final GetAllRecentLocationsUseCase getAllRecentLocationsUseCase;
	private final DataEntryMapper mapper = DataEntryMapper.INSTANCE;

	@GetMapping("/locations")
	public ResponseEntity<List<LocationDTO>> getLocations() {
		return ResponseEntity.ok(mapper.mapToDTO(getAllRecentLocationsUseCase.getAllRecentLocations()));
	}
}
