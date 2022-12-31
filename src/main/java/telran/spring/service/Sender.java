package telran.spring.service;

import telran.view.InputOutput;

public interface Sender {
	void send(InputOutput io, String text);
}
