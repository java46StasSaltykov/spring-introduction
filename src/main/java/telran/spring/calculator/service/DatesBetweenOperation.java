package telran.spring.calculator.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import telran.spring.calculator.dto.OperationData;

@Service("Dates between operation")
public class DatesBetweenOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String[] dates = data.additionalData.split(" ");
		LocalDateTime dateFrom = LocalDateTime.parse(dates[0]);
		LocalDateTime dateTo = LocalDateTime.parse(dates[1]);
		long res = ChronoUnit.DAYS.between(dateFrom, dateTo);
		return Long.toString(res);
	}

}
