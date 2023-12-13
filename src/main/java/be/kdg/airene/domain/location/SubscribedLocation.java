package be.kdg.airene.domain.location;


import lombok.Value;

@Value
public class SubscribedLocation {
	double latitude;
	double longitude;
	String address;

	@Override
	public String toString() {
		return address;
	}
}
