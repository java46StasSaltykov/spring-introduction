package telran.spring.controller;

import java.util.*;
import org.springframework.stereotype.Component;
import telran.spring.service.Sender;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

@Component
public class MessageSender {
	
	Map<String, Sender> senders;
	InputOutput io = new ConsoleInputOutput();

	public MessageSender(Map<String, Sender> senders) {
		this.senders = senders;
	}
	
	public void menu() {
		Item[] items = {
				Item.of("send message", this::send),
				Item.exit()
		};
		Menu menu = new Menu("Sending messages", items);
		menu.perform(io);
	}
	
	void send(InputOutput io) {
		String messageType = io.readString("Enter message type");
		Sender sender = getSenderObject(messageType);
		if (sender != null) {
			String text = io.readString("Enter text");
			sender.send(io, text);
		} else {
			System.out.println("Type doesn't exist");
		}
	}

	private Sender getSenderObject(String messageType) {		
		Sender sender = null;
		for (String key : senders.keySet()) {
			if (key.equalsIgnoreCase(messageType)) {
				sender = senders.get(key);
			}
		}
		return sender;
	}
	
}
