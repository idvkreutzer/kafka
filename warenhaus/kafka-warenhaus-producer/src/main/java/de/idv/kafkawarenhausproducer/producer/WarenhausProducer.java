package de.idv.kafkawarenhausproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.idv.kafkawarenhausproducer.entity.Ware;

@Service
public class WarenhausProducer {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	private ObjectMapper om = new ObjectMapper();
	
	public void sendMessage(Ware warenhaus) throws JsonProcessingException {
		var json = om.writeValueAsString(warenhaus);
		template.send("t_warenhaus", warenhaus.getName(), json);
	}
}
