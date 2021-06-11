package app;

import app.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableConfigurationProperties({ConfigProperties.class})
@ComponentScan(basePackages = "app")
public class SuperchatBackendChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperchatBackendChallengeApplication.class, args);
	}

}
