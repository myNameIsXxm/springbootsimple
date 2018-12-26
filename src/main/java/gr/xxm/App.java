package gr.xxm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "gr.xxm.repository")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
