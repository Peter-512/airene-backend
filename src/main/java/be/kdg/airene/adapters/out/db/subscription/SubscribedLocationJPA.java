package be.kdg.airene.adapters.out.db.subscription;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable public class SubscribedLocationJPA {
	private String address;
	private double latitude;
	private double longitude;
}
