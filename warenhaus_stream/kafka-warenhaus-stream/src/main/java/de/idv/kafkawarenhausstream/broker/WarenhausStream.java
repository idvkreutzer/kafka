package de.idv.kafkawarenhausstream.broker;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import de.idv.kafkawarenhausstream.entity.Ware;

@Configuration
public class WarenhausStream {

	@Bean
	public KStream<String, Ware> kstreamWarenhausKgMapping(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var wareSerde = new JsonSerde<>(Ware.class);

		var wareInKig = builder.stream("t_warenhaus", Consumed.with(stringSerde, wareSerde))
				.mapValues(w -> {
					var ware = new Ware();
					ware.setName(w.getName());
					ware.setPrice(w.getPrice() - w.getPrice() * 0.17);
					ware.setTimestamp(w.getTimestamp());
					ware.setMeasurement(w.getMeasurement());
					return ware;
				});
		
		wareInKig.to("t_warenhaus_tax", Produced.with(stringSerde, wareSerde));
		
		return wareInKig;
	}
	
	

}