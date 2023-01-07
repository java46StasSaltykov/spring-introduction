package telran.spring.calculator.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	
	List<Operation> operationServices;
	Map<String, Operation> operations;
	
	@Value("${app.operation.wrong.name: Wrong name. }")
	String wrongNameMessage;
	@Value("${app.operation.wrong.arithmetic: Wrong operation. }")
	String wrongArithmeticOpMessage;
	@Value("${app.operation.mismatch: Operation mismatch. }")
	String mismatchMessage;

	public CalculatorController(List<Operation> operationServices, Map<String, Operation> operations) {
		this.operationServices = operationServices;
		this.operations = operations;
	}

	@PostMapping
	String getOperationResult(@RequestBody OperationData data) {
		Operation operationService = operations.get(data.operationName);
		String res = operationService != null ? 
				operationService.execute(data) : 
				String.format("Wrong operation name, should be one from %s", operations.keySet());
		return res;

	}

	@GetMapping
	Set<String> getAllOperationNames() {
		return operationServices.stream().map(o -> o.getClass().getName()).collect(Collectors.toSet());
	}
	
	@PostConstruct
	void displayOperations() {
		operationServices.stream().forEach(o -> operations.put(o.getClass().getName(), o));
	}

}