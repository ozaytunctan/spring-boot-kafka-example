# spring-boot-kafka-example

#Kafka kurulumu

<a href='https://kafka.apache.org/quickstart'>download</a>



## Zookeeper server başlatmak için aşağıdaki komutunu çalıştırın

windows

```
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 

```

linux


```
.\bin\zookeeper-server-start.sh  .\config\zookeeper.properties 

```

## Kafka instance başlatmak için 

windows

```
.\bin\windows\kafka-server-start.bat .\config\server.properties 

```
linux

```
.\bin\kafka-server-start.sh .\config\server.properties 

```


## Dependency information

Aşağıdaki dependecy pom.xml dosyasına ekleyin

```java
	<!-- Spring kafka dependency -->
	<dependency>
		<groupId>org.springframework.kafka</groupId>
		<artifactId>spring-kafka</artifactId>
	</dependency>
```
## Kafka Architecture

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Overview_of_Apache_Kafka.svg/677px-Overview_of_Apache_Kafka.svg.png">

## Kafka configs

Kafka Consumers/Producers configurasyonu için projenizin  application.properties/application.yml file 
dosyalarını aşağıdaki satırları ekleyiniz.



```bash

spring.kafka.topic=<topic-name>
spring.kafka.topic.group.id=<consumer-group-id>

## kafka consumer config ##
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=${spring.kafka.topic.group.id}
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
spring.kafka.consumer.properties.spring.deserializer.value.delegate.class: org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.value.default.type=com.java.techhub.kafka.demo.model.UserDetails

## kafka producer config ##
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

## Optional configuration ##
spring.kafka.producer.client-id=producer-${random.uuid}
spring.kafka.consumer.client-id=consumer-${random.uuid}

```


## console consumer/producer

Console üzerinden mesaj göndermek
 
```
./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic otunctan-notification-message-topic
>{"id":656,"content":"sent message content"}

```

Topic consumer log
 
```
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic otunctan-notification-message-topic

```

#Author
```java
OZAY TUNÇTAN
```