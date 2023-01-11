package telran.spring.calculator.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesSimpleOperation implements Operation {
	@Value("${app.message.wrong.dto.fields}")
	String wrongDtoMessage = "";

	@Override
	public String execute(OperationData data) {
		String res = "";
		try {
			DateDaysOperationData dateData = (DateDaysOperationData) data;
			LocalDate date = LocalDate.parse(dateData.date);
			int days = dateData.days;
			if (data.additionalData.equalsIgnoreCase("before")) {
				days = -days;
			}
			res = date.plusDays(days).toString();
			LOG.debug("message: the calculated date is {}", res);
		} catch (ClassCastException e) {
			res = wrongDtoMessage;
			LOG.error("Mismatch - {}", wrongDtoMessage + e.getMessage());
		}
		return res;
	}

	@Override
	public String getOperationName() {
		return "dates-simple";
	}

}
