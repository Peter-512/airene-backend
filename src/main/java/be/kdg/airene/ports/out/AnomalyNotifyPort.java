package be.kdg.airene.ports.out;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.subscription.Subscription;

import java.util.List;

@FunctionalInterface
public interface AnomalyNotifyPort {
	void notifyAnomaly(List<Subscription> subscriptions, Data data);
}
