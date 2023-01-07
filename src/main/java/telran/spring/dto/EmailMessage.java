package telran.spring.dto;

import jakarta.validation.constraints.*;

public class EmailMessage extends Message {
	
	@Email @NotEmpty
	public String emailAddress;

}
