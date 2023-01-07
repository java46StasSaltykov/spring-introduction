package telran.spring.service;

import org.springframework.stereotype.Service;
import telran.spring.dto.*;

@Service
public class SmsSender implements Sender {

	@Override
	public String send(Message message) {
		SmsMessage smsMessage = (SmsMessage) message;
		return String.format("text '%s' has been sent to %s\n", 
				smsMessage.text, smsMessage.phoneNumber);
	}
}