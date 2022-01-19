package tr.com.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import tr.com.demo.constants.KafkaConstants;

@Component
public class MessagePublisherService implements Publisher {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	private static final Logger logger = LoggerFactory.getLogger(MessagePublisherService.class);

	public MessagePublisherService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	
	/**
	 * 
	 */
	@Override
	public <T> void publish(String topicName, T data) {
		doPublish(topicName, data);
	}
	
	@Override
	public <T> void publish(T data) {
		doPublish(KafkaConstants.DEFAULT_TOPIC_NAME, data);
	}
	
	
	private  <T>  void doPublish(String topicName, T data) {
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, data);

		// 
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				logger.info("Sent message=[" + data + "] with offset=[" + result.getRecordMetadata().offset() + "]");

			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Unable to send message=[" + data + "] due to : " + ex.getMessage());

			}
		});

	}

}
