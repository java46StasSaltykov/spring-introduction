package telran.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import telran.spring.controller.MessageSender;
import telran.spring.dto.*;

@WebMvcTest(MessageSender.class)
class MessageSenderTest {
	
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void rightDataControllerTest() throws Exception {
		EmailMessage message = new EmailMessage();
		message.type = "email";
		message.text = "any text";
		message.emailAddress = "test@gmail.com";
		String messageJSON = mapper.writeValueAsString(message);
		mockMvc.perform(post("http://localhost:8080/messages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(messageJSON)).andExpect(status().isOk());
	}
	
	@Test
	void wrongDataControllerTest() throws Exception {
		EmailMessage message = new EmailMessage();
		message.type = "email";
		message.text = "any text";
		message.emailAddress = "test";
		String messageJSON = mapper.writeValueAsString(message);
		mockMvc.perform(post("http://localhost:8080/messages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(messageJSON)).andExpect(status().isBadRequest());
	}

}
