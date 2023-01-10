package telran.spring.controller;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.dto.Message;
import telran.spring.service.Sender;

@RestController
@RequestMapping("messages")
public class MessageSender {
	
	static Logger LOG = LoggerFactory.getLogger(MessageSender.class);
	
	Map<String, Sender> senders;
	List<Sender> sendersList;
	@Value("${app.message.wrong.type: Wrong Type }")
	String wrongTypeMessage;

	public MessageSender(Map<String, Sender> senders, List<Sender> sendersList) {

		this.senders = senders;
		this.sendersList = sendersList;
	}

	@PostMapping
	String sendMessage(@RequestBody @Valid Message message) {
		LOG.debug("recieved request for sending text: {}, sender type: {}", message.text, message.type);
		Sender sender = senders.get(message.type);
		return sender != null ? sender.send(message) : wrongTypeMessage + message.type;
	}

	@GetMapping
	Set<String> getTypes() {
		return senders.keySet();
	}

	@PostConstruct
	void displayTypes() {
		LOG.info("application context is created with types {}", senders.keySet());
	}

	@PreDestroy
	void shutdown() {
		System.out.println("Bye performed graceful shutdown");
		LOG.info("shutdown performed");
	}

}