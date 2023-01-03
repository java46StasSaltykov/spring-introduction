package telran.spring.service;

import org.springframework.stereotype.Service;
import telran.spring.dto.*;

@Service("email")
public class EmailsSender implements Sender {

	@Override
	public String send(Message message) {
		EmailMessage emailMessage = (EmailMessage) message;
		return String.format("Email text '%s' has been sent to %s\n", 
				emailMessage.text, emailMessage.emailAddress);
	}
}