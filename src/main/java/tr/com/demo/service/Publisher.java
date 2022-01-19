package tr.com.demo.service;

/**
 * 
 * @author otunctan
 *
 */

public interface Publisher {

	public  <T> void publish(String topicName,T data);
	
	public  <T> void publish(T data);

}
