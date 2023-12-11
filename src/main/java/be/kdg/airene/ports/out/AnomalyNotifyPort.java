package be.kdg.airene.ports.out;

import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.user.User;

import java.util.List;

@FunctionalInterface
public interface AnomalyNotifyPort {
	void notifyAnomaly(List<User> users, Anomaly anomaly);
}
