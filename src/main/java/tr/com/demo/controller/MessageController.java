package tr.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.demo.model.Message;
import tr.com.demo.service.MessageService;

@RestController
@RequestMapping(path = "/api/v1/message")
public class MessageController {
	
	
	@Autowired
	private MessageService messageService;
	
	
	
	@PostMapping("/send")
	public ResponseEntity<Message> sendMessage(@RequestBody Message message){
		return ResponseEntity.ok().body(messageService.send(message));
	}

}
