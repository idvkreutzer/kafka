package de.idv.kafkawarenhausconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.idv.kafkawarenhausconsumer.entity.Ware;

@Service
public class WarenhausConsumerDashboardTax {
	private Logger log = LoggerFactory.getLogger(WarenhausConsumerDashboardTax.class);

	private ObjectMapper om = new ObjectMapper();

	@KafkaListener(topics = "t_warenhaus_tax")
	public void consume(String message) throws InterruptedException, JsonMappingException, JsonProcessingException {
		Ware ware = om.readValue(message, Ware.class);
		log.info("Nach Steuerabzug: {}", ware);
	}
}
