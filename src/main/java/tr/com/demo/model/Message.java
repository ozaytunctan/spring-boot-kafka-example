package tr.com.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -722390923503942749L;

	private Long id;

	private String content;

	private LocalDateTime createdDate = LocalDateTime.now();

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return new StringBuilder("{")//
				.append("id:" + this.id)//
				.append(",content:" + this.content)//
				.append(",createdDate:" + this.createdDate).append("}").toString();
	}

}
