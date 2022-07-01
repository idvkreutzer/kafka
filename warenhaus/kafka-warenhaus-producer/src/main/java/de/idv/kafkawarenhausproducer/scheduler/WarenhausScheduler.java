package de.idv.kafkawarenhausproducer.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.idv.kafkawarenhausproducer.entity.Ware;
import de.idv.kafkawarenhausproducer.producer.WarenhausProducer;

@Service
public class WarenhausScheduler {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private WarenhausProducer producer;
	
	@Scheduled(fixedRate =  5000)
	public void fetchCommodities() {
		var waren = restTemplate.exchange("http://localhost:8080/api/warenhaus/v1/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ware>>() {
		}).getBody();
		
		waren.forEach(c -> {
			try {
				producer.sendMessage(c);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}
	
}
