package telran.spring.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import telran.spring.calculator.controller.CalculatorController;
import telran.spring.calculator.dto.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getMappingTest() throws Exception {
		Set<String> response = new HashSet();
		String json = mapper.writeValueAsString(response);
		mockMvc.perform(get("http://localhost:8080/calculator").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void correctDataTest() throws Exception {
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
		dateDaysData.days = 100;
		
		List<OperationData> operationDataObjects = new ArrayList<>();
		operationDataObjects.add(arithmeticData);
		operationDataObjects.add(dateDaysData);
		operationDataObjects.add(datesData);

		operationDataObjects.stream().forEach(o -> {
			try {
				testOperation(o);
			} catch (Exception e) {

			}
		});
	}
	
	@Test
	void wrongDataTest() throws Exception {
		DatesOperationData datesData = new DatesOperationData();
		datesData.operationName = "dates-between";
		datesData.additionalData = "";
		datesData.dateTo = "abc";
		datesData.dateFrom = "2023-01-01";
		
		String operationJSON = mapper.writeValueAsString(datesData);
		mockMvc.perform(post("http://localhost:8080/calculator")
				.contentType(MediaType.APPLICATION_JSON)
				.content(operationJSON)).andExpect(status().isBadRequest());
	}

	private Object testOperation(OperationData o) throws Exception {
		String operationJSON = mapper.writeValueAsString(o);
		return mockMvc.perform(post("http://localhost:8080/calculator")
				.contentType(MediaType.APPLICATION_JSON)
				.content(operationJSON)).andExpect(status().isOk());
	}

}
