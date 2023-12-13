package be.kdg.airene.adapters.in.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubscriptionDTO {
	private UUID id;
	private double latitude;
	private double longitude;
	private String address;
}
