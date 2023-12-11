package be.kdg.airene.adapters.out.websocket;

import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.out.AnomalyNotifyPort;

import java.util.List;

public class WebsocketNotificationSender implements AnomalyNotifyPort {
	@Override
	public void notifyAnomaly(List<User> users, Anomaly anomaly) {

	}
}
