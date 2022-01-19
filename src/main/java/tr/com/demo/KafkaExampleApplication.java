package tr.com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
//@EnableKafka
public class KafkaExampleApplication {

	
	private static final Logger logger=LoggerFactory.getLogger(KafkaExampleApplication.class);
	
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(KafkaExampleApplication.class, args);
		
		logger.info("========================>KafkaExampleApplication started...=======================");
	}

}
