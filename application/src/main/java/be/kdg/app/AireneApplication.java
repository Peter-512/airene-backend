package be.kdg.app;

import be.kdg.common.annotations.RabbitMQConfig;
import be.kdg.common.annotations.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;

@EnableFeignClients
@EnableRetry
@EnableCaching
//@EnableScheduling
@SpringBootApplication
@ComponentScan (excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = SpringBootApplication.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = RabbitMQConfig.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = SecurityConfiguration.class),
})
public class AireneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AireneApplication.class, args);
	}
}
