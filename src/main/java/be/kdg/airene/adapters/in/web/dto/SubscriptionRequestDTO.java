package be.kdg.airene.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull
public class SubscriptionRequestDTO {
	private double latitude;
	private double longitude;
	private String address;
}
