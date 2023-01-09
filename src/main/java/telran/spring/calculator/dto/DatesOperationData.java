package telran.spring.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DatesOperationData extends OperationData {
	@NotNull
	@Pattern(regexp = OperationData.DATE_PATTERN)
	public String dateFrom;
	@NotNull
	@Pattern(regexp = OperationData.DATE_PATTERN)
	public String dateTo;
}
