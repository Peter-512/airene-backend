package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.LocationDTO;
import be.kdg.airene.adapters.out.mapper.DataEntryMapper;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.ports.in.GetAllRecentLocationsUseCase;
import be.kdg.airene.ports.in.SubscribeToLocationUseCase;
import be.kdg.airene.ports.in.UnsubscribeFromLocationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

	private final GetAllRecentLocationsUseCase getAllRecentLocationsUseCase;
	private final SubscribeToLocationUseCase subscribeToLocationUseCase;
	private final UnsubscribeFromLocationUseCase unsubscribeFromLocationUseCase;
	private final DataEntryMapper mapper = DataEntryMapper.INSTANCE;
	private final LocationMapper locationMapper = LocationMapper.INSTANCE;

	@GetMapping
	public ResponseEntity<List<LocationDTO>> getLocations() {
		return ResponseEntity.ok(mapper.mapToDTO(getAllRecentLocationsUseCase.getAllRecentLocations()));
	}
}
