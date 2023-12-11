package be.kdg.airene.config.mail;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailSenderConfig {

	@Value("${azure.mail.endpoint}")
	private String endpoint;
	@Value("${azure.mail.sender}")
	private String senderAddress;

	@Bean
	public EmailClient emailClient() {
		return new EmailClientBuilder()
				.endpoint(endpoint)
				.credential(new DefaultAzureCredentialBuilder().build())
				.buildClient();
	}

	@Bean String senderAddress() {
		return senderAddress;
	}
}
