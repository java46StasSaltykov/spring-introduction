package telran.spring.calculator.dto;

import jakarta.validation.constraints.*;

public class DatesOperationData extends OperationData {
	
	@NotEmpty @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	public String dateFrom;
	@NotEmpty @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	public String dateTo;

}
