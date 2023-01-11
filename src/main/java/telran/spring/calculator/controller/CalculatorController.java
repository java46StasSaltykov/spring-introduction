package telran.spring.calculator.controller;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

	List<Operation> operations;
	Map<String, Operation> operationServices;
	@Value("${app.message.wrong.operation.name}")
	String wrongOperationMessage;
	static Logger LOG = LoggerFactory.getLogger(CalculatorController.class);

	public CalculatorController(List<Operation> operations) {
		this.operations = operations;
	}

	@PostMapping
	String getOperationResult(@RequestBody @Valid OperationData data) {
		String res = "";
		Operation operationService = operationServices.get(data.operationName);
		if (operationService != null) {		
			LOG.debug("recieved operation request {}", data.operationName);
			res = operationService.execute(data);
		} else {
			res = String.format(wrongOperationMessage + " %s", operationServices.keySet());
			LOG.error(wrongOperationMessage + operationServices.keySet());
		}
		return res;

	}

	@GetMapping
	Set<String> getAllOperationNames() {
		return operationServices.keySet();
	}

	@PostConstruct
	void createMapOperationsServices() {
		operationServices = operations.stream().collect(Collectors.toMap(Operation::getOperationName, service -> service));
		LOG.info("application context is created with operations: {}", operationServices.keySet());
	}

}
