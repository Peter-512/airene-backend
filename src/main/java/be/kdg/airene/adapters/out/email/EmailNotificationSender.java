package be.kdg.airene.adapters.out.email;

import be.kdg.airene.domain.data.Data;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.out.AnomalyNotifyPort;
import com.azure.communication.email.EmailClient;
import com.azure.communication.email.models.EmailMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EmailNotificationSender implements AnomalyNotifyPort {

	private final EmailClient mailSender;
	private String senderAddress;
	@Override
	public void notifyAnomaly(List<Subscription> subscriptions, Data data) {
		log.debug("Sending mail using sender address: {}", senderAddress);
		subscriptions.parallelStream().forEach(subscription -> {
			EmailMessage emailMessage = new EmailMessage()
					.setSenderAddress(senderAddress)
					.setToRecipients(subscription.getUser().getEmail())
					.setSubject("Anomaly detected near you!")
					.setBodyHtml("""
       							<html>
								<body>
									<h1>Anomaly detected near you!</h1>
									<p>Dear %s,</p>
									<p>There has been an anomaly detected near you at %s.</p>
									<p>Check the web app for more information.</p>
									<p>Details of the anomaly:</p>
									%s
									<p>Kind regards,</p>
									<p>The Airene team</p>
								</body>
								</html>
	   							""".formatted(subscription.getUser().getName(), subscription.getLocation(), data));
			mailSender.beginSend(emailMessage);
		});
	}
}
