package com.onlineorder.foodiefleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class FoodiefleetApplication  implements ApplicationRunner {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		for(int i = 0; i < 10; ++i) {
			kafkaTemplate.send("foodiefleet", "Welcome FoodieFleet!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(FoodiefleetApplication.class, args);
		//sendMessage("Hello There")
	}
	@KafkaListener(topics = "foodiefleet", groupId = "group-id")
	public void listen(String message) {
		System.out.println("Received Messasge in group - group-id: " + message);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		sendMessage("Hi, your order has been received. Will be back in a minute!");
	}
}
