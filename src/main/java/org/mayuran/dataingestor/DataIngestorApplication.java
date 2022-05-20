package org.mayuran.dataingestor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@SpringBootApplication
public class DataIngestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataIngestorApplication.class, args);
	}
	
	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

	
	@Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

	@Bean
	public NewTopic topic() {
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		String topicName = "mayuran-" + new SimpleDateFormat("dd").format(date) + "-"
				+ new SimpleDateFormat("MM").format(date);
		return TopicBuilder.name(topicName).partitions(10).replicas(1).build();
	}

	@Bean
	public ProducerFactory<Integer, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.109:30001");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		// See https://kafka.apache.org/documentation/#producerconfigs for more
		// properties
		return props;
	}

	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate() {
		return new KafkaTemplate<Integer, String>(producerFactory());
	}

	/*
	 * @Bean public KafkaAdmin admin() { Map<String, Object> configs = new
	 * HashMap<>(); configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * "localhost:9092"); return new KafkaAdmin(configs); }
	 */
}
