package telran.spring.calculator.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import telran.spring.calculator.service.ArithmeticSimpleOperation;
import telran.spring.calculator.service.DatesBetweenOperation;
import telran.spring.calculator.service.DatesSimpleOperation;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@Type(ArithmeticSimpleOperation.class), @Type(DatesBetweenOperation.class), @Type(DatesSimpleOperation.class)})
public class OperationData {
	
	public String operationName;
	public String additionalData;

}
