package be.kdg.airene.adapters.out.db.subscription;

import be.kdg.airene.adapters.out.db.user.UserJPA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserJPA user;
	@Embedded
	private SubscribedLocationJPA location;
	private boolean isEnabled = true;
	private LocalDateTime createdAt = LocalDateTime.now();
}
