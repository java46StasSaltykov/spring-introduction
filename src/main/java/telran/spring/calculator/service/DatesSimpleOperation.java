package telran.spring.calculator.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.OperationData;

@Service("Simple date operation")
public class DatesSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String[] dateAndDays = data.additionalData.split(" ");
		LocalDate date = LocalDate.parse(dateAndDays[0]);
		int days = Integer.parseInt(dateAndDays[1]);
		LocalDate res = date.plusDays(days);
		return res.toString();
	}

}
