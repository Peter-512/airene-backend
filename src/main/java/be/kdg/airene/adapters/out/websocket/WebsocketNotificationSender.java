package be.kdg.airene.adapters.out.websocket;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.out.AnomalyNotifyPort;

import java.util.List;

public class WebsocketNotificationSender implements AnomalyNotifyPort {
	@Override
	public void notifyAnomaly(List<Subscription> subscriptions, Data data) {

	}
}
