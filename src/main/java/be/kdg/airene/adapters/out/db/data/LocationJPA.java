package be.kdg.airene.adapters.out.db.data;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable public class LocationJPA {
	private double latitude;
	private double longitude;
}
