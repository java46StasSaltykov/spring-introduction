package telran.spring.dto;

import jakarta.validation.constraints.*;

public class TcpMessage extends Message {
	
	public String ipAddress;
	@Min(1024) @Max(50000)
	public String port;

}
