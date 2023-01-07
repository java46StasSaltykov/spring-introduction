package telran.spring;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.spring.controller.MessageSender;

@SpringBootApplication
public class SpringArchitectureApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ct = SpringApplication.run(SpringArchitectureApplication.class, args);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("For shutdown type 'exit'");
			String line = scanner.nextLine();
			if (line.equalsIgnoreCase("exit")) {
				break;
			}
		}
		ct.close();
		scanner.close();
	}

}
