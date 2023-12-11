package be.kdg.airene.adapters.out.email;

import be.kdg.airene.domain.anomaly.Anomaly;
import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.out.AnomalyNotifyPort;
import com.azure.communication.email.EmailClient;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.SyncPoller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailNotificationSender implements AnomalyNotifyPort {

	private final EmailClient mailSender;
	private String senderAddress;
	@Override
	public void notifyAnomaly(List<User> users, Anomaly anomaly) {
		EmailMessage emailMessage = new EmailMessage()
				.setSenderAddress(senderAddress)
				.setToRecipients(users.stream().map(User::getEmail).toArray(String[]::new))
				.setSubject("Anomaly detected")
				.setBodyHtml("<h1>Anomaly detected</h1><p>There is an anomaly detected in an area you've subscribed to.</p>");

		SyncPoller<EmailSendResult, EmailSendResult> poller = mailSender.beginSend(emailMessage);
	}
}
