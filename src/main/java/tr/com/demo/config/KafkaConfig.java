package tr.com.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import tr.com.demo.constants.KafkaConstants;

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

//	/**
//	 * Admin Client için
//	 * @return
//	 */
//	@Bean 
//	public NewTopic notificationTopic() {
//		return TopicBuilder.name(KafkaConstants.NOTIFICATION_MESSAGE_TOPIC)
//				.replicas(2)
//				.partitions(2)
//				.build();
//	}
	
	
	
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> producerConfigs = new HashMap<>(kafkaProperties.buildProducerProperties());
		// ekstra bir ayar yapılacak ya burdan eklenebilir yada properties dosyasından

		return producerConfigs;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		kafkaTemplate.setTransactionIdPrefix("otunctan-");
		return kafkaTemplate;
	}

	@Bean
	public StringJsonMessageConverter jsonMessageConverter() {
		return new StringJsonMessageConverter();
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> consumerConfigs = new HashMap<>(kafkaProperties.buildConsumerProperties());
		// ekstra bir ayar yapılacak ya burdan eklenebilir yada properties dosyasından
		// consumerConfigs.put()
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
