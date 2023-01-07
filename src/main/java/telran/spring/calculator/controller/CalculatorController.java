package telran.spring.calculator.controller;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	
	List<Operation> operationServices;
	Map<String, Operation> operations;
	
	@Value("${app.operation.wrong.name: Wrong name. }")
	String wrongNameMessage;

	@Value("${app.operation.mismatch: Operation mismatch. }")
	String mismatchMessage;

	public CalculatorController(List<Operation> operationServices, Map<String, Operation> operations) {
		this.operationServices = operationServices;
		this.operations = operations;
	}

	@PostMapping
	String getOperationResult(@RequestBody @Valid OperationData data) {
		String res = "";
		try {
			Operation operationService = operations.get(data.operationName);
			res = operationService != null ? operationService.execute(data) : wrongNameMessage;
		} catch (ClassCastException e) {
			res = mismatchMessage;
		}
		return res;
	}

	@GetMapping
	Set<String> getAllOperationNames() {
		return operations.keySet();
	}
	
	@PostConstruct
	void displayOperations() {
		operations = operationServices.stream().collect(Collectors.toMap(o -> o.getClass().getSimpleName(), o -> o));

	}

}