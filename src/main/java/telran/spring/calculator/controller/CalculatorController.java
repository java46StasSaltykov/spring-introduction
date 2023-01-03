package telran.spring.calculator.controller;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("operations")
public class CalculatorController {

	Map<String, Operation> operations;

	public CalculatorController(Map<String, Operation> operations) {
		this.operations = operations;
	}
	
	@PostMapping
	String performOperation(@RequestBody OperationData operationData) {
		Operation operation = operations.get(operationData.operationName);
		return operation.execute(operationData);	
	}
	
	@GetMapping
	Set<String> getOperations() {
		return operations.keySet();
	}
	
}
