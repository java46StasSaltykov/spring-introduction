package telran.spring.calculator.service;

import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.OperationData;

@Service("arithmetic operation")
public class ArithmeticSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		double res = 0;
		String[] operands = data.additionalData.split(" ");
		double operand1 = Double.parseDouble(operands[0]);
		double operand2 = Double.parseDouble(operands[2]);
		switch (data.additionalData.charAt(1)) {
		case '+':
			res = operand1 + operand2;
			break;
		case '-':
			res = operand1 - operand2;
			break;
		case '*':
			res = operand1 * operand2;
			break;
		case '/':
			res = operand1 / operand2;
			break;
		default:
			break;
		}
		return Double.toString(res);
	}

}
