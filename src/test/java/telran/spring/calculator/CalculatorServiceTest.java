package telran.spring.calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.service.*;

@SpringBootTest
class CalculatorServiceTest {

	@Autowired
	ArithmeticSimpleOperation arithmeticSimpleOperation;
	@Autowired
	DatesBetweenOperation datesBetweenOperation;
	@Autowired
	DatesSimpleOperation datesSimpleOperation;

	@Test
	void correctOperationsExecutionTest() {
		ArithmeticOperationData arithmeticData = new ArithmeticOperationData();
		arithmeticData.operationName = "arithmetic-simple";
		arithmeticData.additionalData = "+";
		arithmeticData.operand1 = 5.0;
		arithmeticData.operand2 = 5.0;

		DatesOperationData datesData = new DatesOperationData();
		datesData.operationName = "dates-between";
		datesData.additionalData = "";
		datesData.dateTo = "2023-01-10";
		datesData.dateFrom = "2023-01-01";

		DateDaysOperationData dateDaysData = new DateDaysOperationData();
		dateDaysData.operationName = "dates-simple";
		dateDaysData.additionalData = "before";
		dateDaysData.date = "2023-01-10";
		dateDaysData.days = 10;

		assertEquals(arithmeticSimpleOperation.execute(arithmeticData), "10.0");
		assertEquals(datesBetweenOperation.execute(datesData), "9");
		assertEquals(datesSimpleOperation.execute(dateDaysData), "2022-12-31");
	}

	@Test
	void operationsWrongExecutionTest() {
		ArithmeticOperationData wrongArithmeticData = new ArithmeticOperationData();
		wrongArithmeticData.operationName = "dates-between";
		wrongArithmeticData.additionalData = "+";
		wrongArithmeticData.operand1 = 5.0;
		wrongArithmeticData.operand2 = 5.0;
		assertTrue(datesBetweenOperation.execute(wrongArithmeticData).contains("mismatches"));
	}
}
