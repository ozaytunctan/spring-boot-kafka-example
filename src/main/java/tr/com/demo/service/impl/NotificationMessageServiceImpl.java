
package tr.com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import tr.com.demo.constants.KafkaConstants;
import tr.com.demo.model.Message;
import tr.com.demo.service.MessagePublisherService;
import tr.com.demo.service.NotificationMessageService;

/**
 * 
 * @author otunctan
 *
 */
@Service
public class NotificationMessageServiceImpl implements NotificationMessageService {

	
	private static final Logger logger =LoggerFactory.getLogger(NotificationMessageServiceImpl.class);
	
	private final MessagePublisherService messagePublisherService;

	public NotificationMessageServiceImpl(MessagePublisherService messagePublisherService) {
		this.messagePublisherService = messagePublisherService;
	}

	@Override
	public Message send(Message message) {
		messagePublisherService.publish(KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,message.getId()+"",message);
		return message;
	}
	
	
	@KafkaListener(topics = KafkaConstants.DEFAULT_TOPIC_NAME,groupId = KafkaConstants.DEFAULT_TOPIC_GROUP_ID)
	public void receiveMessage(Message message) {
		logger.info(message.toString());
	}
	
	@KafkaListener(topics = KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,containerFactory = "kafkaListenerContainerFactory")
	public void receiveNotificationMessage(Message message) {
		logger.info(message.toString());
		
	}
	
//	@KafkaListener(id="listenPartion01",
//			topicPartitions = {@TopicPartition(topic = KafkaConstants.NOTIFICATION_MESSAGE_TOPIC,partitions = {"0","1"})},
//			containerFactory = "kafkaListenerContainerFactory")
//	public void receiveNotificationMessagePartion(Message message) {
//		logger.info(message.toString());
//		
//	}


}
