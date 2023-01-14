package telran.spring.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import telran.spring.calculator.dto.*;
import telran.spring.calculator.service.*;

@SpringBootTest
class CalculatorOperationsTest {
	
	@Autowired
	Map<String, Operation> operationServices;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void arithmeticSimpleRightTest() {
		Operation operation = operationServices.get("arithmeticSimpleOperation");

		ArithmeticOperationData data = new ArithmeticOperationData();
		data.additionalData = "*";
		data.operand1 = 10.0;
		data.operand2 = 2.0;
		assertEquals("20.0", operation.execute(data));
	}

	@Test
	void arithmeticSimpleWrongTest() {
		Operation operation = operationServices.get("arithmeticSimpleOperation");
		DatesOperationData data = new DatesOperationData();
		assertTrue(operation.execute(data).contains("mismatches"));
	}

}
