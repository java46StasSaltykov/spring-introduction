package telran.spring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import telran.spring.dto.*;
import telran.spring.service.*;

@SpringBootTest
class MessageServicesTest {
	
	@Autowired
	EmailsSender emailsSender;
	@Autowired
	SmsSender smsSender;
	@Autowired
	TcpSender tcpSender;

	@Test
	void emailSenderTest() {
		EmailMessage message = new EmailMessage();
		message.type = "email";
		message.text = "any text";
		message.emailAddress = "test@gmail.com";
		assertTrue(emailsSender.send(message).contains("has been sent"));
	}

	@Test
	void emailSenderWrongDtoTest() {
		TcpMessage message = new TcpMessage();
		message.type = "email";
		message.text = "any text";
		message.ipAddress = "1.2.3.4";
		message.port = "4000";
		assertTrue(emailsSender.send(message).contains("missmatch"));
	}

}