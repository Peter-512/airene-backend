package be.kdg.airene.domain.user;

import be.kdg.airene.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {
	private UUID id;
	private String email;
	private List<Location> subscribedLocations = new ArrayList<>();
}
