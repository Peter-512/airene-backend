package be.kdg.airene.adapters.out.db.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "subscribed_location")
@Getter
@Setter
public class SubscribedLocationJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private double latitude;
	private double longitude;
	@Column(name = "user_id", nullable = false, updatable = false, insertable = false)
	private UUID userId;
}
