package be.kdg.airene.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull
public class CoordinateDTO {
	private double latitude;
	private double longitude;
}
