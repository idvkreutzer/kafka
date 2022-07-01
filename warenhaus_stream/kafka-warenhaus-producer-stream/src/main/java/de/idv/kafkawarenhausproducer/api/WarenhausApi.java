package de.idv.kafkawarenhausproducer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.idv.kafkawarenhausproducer.entity.Ware;
import de.idv.kafkawarenhausproducer.service.WarenhausService;

@RestController
@RequestMapping("api/warenhaus/v1")
public class WarenhausApi {

	@Autowired
	private WarenhausService warenhausService;
	
	@GetMapping(value = "/all")
	public List<Ware> generateWarenhaus(){
		return warenhausService.createDummyWarenhaus();
	}
}
