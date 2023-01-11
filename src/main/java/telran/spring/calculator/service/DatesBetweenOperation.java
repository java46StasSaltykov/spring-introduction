package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesBetweenOperation implements Operation {
	@Value("${app.message.wrong.dto.fields}")
	String wrongDtoMessage = "";

	@Override
	public String execute(OperationData data) {
		String res = "";
		try {
			DatesOperationData datesData = (DatesOperationData) data;
			LocalDate dateFrom = LocalDate.parse(datesData.dateFrom);
			LocalDate dateTo = LocalDate.parse(datesData.dateTo);
			res = ChronoUnit.DAYS.between(dateFrom, dateTo) + "";
			LOG.debug("message: the number of days between dates is {}", res);
		} catch (ClassCastException e) {
			res = wrongDtoMessage;
			LOG.error("Mismatch - {}", wrongDtoMessage + e.getMessage());
		}
		return res;
	}

	@Override
	public String getOperationName() {
		return "dates-between";
	}

}
