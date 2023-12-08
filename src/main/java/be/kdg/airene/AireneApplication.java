package be.kdg.airene;

import com.azure.spring.messaging.implementation.annotation.EnableAzureMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableAzureMessaging
@SpringBootApplication
public class AireneApplication {
	public static void main(String[] args) {
		 SpringApplication.run(AireneApplication.class, args);
	}
}
