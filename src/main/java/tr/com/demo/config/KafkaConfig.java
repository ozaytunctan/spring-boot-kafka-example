package tr.com.demo.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * 
 * @author otunctan
 *
 */
@EnableKafka
@Configuration
public class KafkaConfig {

//	@Value("${spring.kafka.bootstrap-servers}")
//	private String bootstrapServers;

	@Autowired
	KafkaProperties kafkaProperties;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> consumerCOnfigs = kafkaProperties.buildProducerProperties();
		// ekstra bir ayar yapılacak ya burdan eklenebilir yada properties dosyasından

		return consumerCOnfigs;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		kafkaTemplate.setTransactionIdPrefix("btx");
		return kafkaTemplate;
	}

	@Bean
	public StringJsonMessageConverter jsonMessageConverter() {
		return new StringJsonMessageConverter();
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> consumerConfigs = kafkaProperties.buildConsumerProperties();
		// ekstra bir ayar yapılacak ya burdan eklenebilir yada properties dosyasından
		//consumerConfigs.put()
		return consumerConfigs;
	}

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setMessageConverter(jsonMessageConverter());
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
