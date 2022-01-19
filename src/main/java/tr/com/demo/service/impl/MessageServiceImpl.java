
package tr.com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import tr.com.demo.constants.KafkaConstants;
import tr.com.demo.model.Message;
import tr.com.demo.service.MessagePublisherService;
import tr.com.demo.service.MessageService;

/**
 * 
 * @author otunctan
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	
	private static final Logger logger =LoggerFactory.getLogger(MessageServiceImpl.class);
	
	private final MessagePublisherService messagePublisherService;

	public MessageServiceImpl(MessagePublisherService messagePublisherService) {
		this.messagePublisherService = messagePublisherService;
	}

	@Override
	public Message send(Message message) {
		messagePublisherService.publish(KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,message);
		return message;
	}
	
	
	@KafkaListener(topics = KafkaConstants.DEFAULT_TOPIC_NAME,groupId = KafkaConstants.DEFAULT_TOPIC_GROUP_ID)
	public void receiveMessage(Message message) {
		logger.info(message.toString());
	}
	
	@KafkaListener(topics = KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,containerFactory = "kafkaListenerContainerFactory")
	//@KafkaListener(topics = KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,groupId = KafkaConstants.DEFAULT_TOPIC_GROUP_ID)
	public void receiveNotificationMessage(Message message) {
		logger.info(message.toString());
		
	}


}
