package telran.spring.calculator.service;

import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.OperationData;

@Service("arithmetic operation")
public class ArithmeticSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		
		return null;
	}

}
