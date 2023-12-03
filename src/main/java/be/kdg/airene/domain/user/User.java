package be.kdg.airene.domain.user;

import be.kdg.airene.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
	private UserID userID;
	private List<Location> subscribedLocations;
}
