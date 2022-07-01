package de.idv.kafkawarenhausproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaWarenhausProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaWarenhausProducerApplication.class, args);
	}

}
