package tr.com.demo.service;

/**
 * 
 * @author otunctan
 *
 */

public interface Publisher {

	/**
	 * 
	 * @param <T>
	 * @param <K>
	 * @param topicName  Mesajların tutulduğu yer 
	 * @param key  
	 * @param data message
	 */
	public  <T,K> void publish(String topicName,String key, T data);
	
	/**
	 * 
	 * @param <T>
	 * @param topicName
	 * @param data
	 */
	public  <T> void publish(String topicName,T data);
	
	/**
	 * 
	 * @param <T>
	 * @param data
	 */
	public  <T> void publish(T data);

}
