package de.idv.kafkawarenhausproducer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import de.idv.kafkawarenhausproducer.entity.Ware;

@Service
public class WarenhausService {

	private static final Map<String, Ware> WARENHAUS_BASE = new HashMap<>();
	private static final String KUPFER = "kupfer";
	private static final String GOLD = "gold";

	// max adjustment for price
	private static final double MAX_ADJUST = 1.05d;

	// min adjustment for price
	private static final double MIN_ADJUST = 0.95d;

	static {
		var timestamp = System.currentTimeMillis();

		WARENHAUS_BASE.put(GOLD, new Ware(GOLD, 1047.25, "gramm", timestamp));
		WARENHAUS_BASE.put(KUPFER, new Ware(KUPFER, 5900.57, "tonne", timestamp));
	}

	public Ware createDummyWarenhaus(String name) {
		if (!WARENHAUS_BASE.keySet().contains(name)) {
			throw new IllegalArgumentException("Warenhaus invalid " + name);
		}

		Ware Warenhaus = WARENHAUS_BASE.get(name);
		var basePrice = Warenhaus.getPrice();
		var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUST, MAX_ADJUST);

		Warenhaus.setPrice(newPrice);
		Warenhaus.setTimestamp(System.currentTimeMillis());

		return Warenhaus;

	}

	public List<Ware> createDummyWarenhaus() {
		List<Ware> result = new ArrayList<>();
		WARENHAUS_BASE.keySet().forEach(c -> result.add(createDummyWarenhaus(c)));

		return result;
	}
}
