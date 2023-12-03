package be.kdg.airene.adapters.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/anomalies")
public class AnomalyController {

	@GetMapping
	public ResponseEntity<?> getAnomalies() {
		return ResponseEntity.ok().build();
	}
}
