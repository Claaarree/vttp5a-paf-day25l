package vttp5a_paf.day25l_producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vttp5a_paf.day25l_producer.model.Todo;
import vttp5a_paf.day25l_producer.service.ProducerService;

@SpringBootApplication
public class Day25lProducerApplication {
	
	@Autowired
	static ProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(Day25lProducerApplication.class, args);

		for(int i = 0; i < 1000; i++) {
			Todo todo = new Todo();
			todo.setId(i);
			todo.setTaskName("Task " + i);
			producerService.sendMessage(todo);
		}
	}

}
