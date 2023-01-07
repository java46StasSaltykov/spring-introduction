package telran.spring.service;

import org.springframework.stereotype.Service;
import telran.spring.dto.*;

@Service
public class EmailsSender implements Sender {

	@Override
	public String send(Message message) {
		String res = "";
		EmailMessage emailMessage;
		try {
			emailMessage = (EmailMessage) message;
			res = String.format("Email text '%s' has been sent to %s\n", 
					emailMessage.text, emailMessage.emailAddress);
		} catch (Exception e) {
			res = "Message Data missmatch sender type";
		}
		return res;
	}
}