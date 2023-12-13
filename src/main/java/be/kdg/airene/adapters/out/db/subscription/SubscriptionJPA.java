package be.kdg.airene.adapters.out.db.subscription;

import be.kdg.airene.adapters.out.db.data.LocationJPA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "subscription")
@Getter
@Setter
public class SubscriptionJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "user_id")
	private UUID userId;
	@Embedded
	private LocationJPA location;
}
