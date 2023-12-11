package be.kdg.airene.adapters.out.db.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJPA {
	@Id
	private UUID id;
	private String name;
	private String email;
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<SubscribedLocationJPA> locations;
}
