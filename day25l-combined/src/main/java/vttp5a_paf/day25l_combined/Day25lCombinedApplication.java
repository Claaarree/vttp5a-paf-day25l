package vttp5a_paf.day25l_combined;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp5a_paf.day25l_combined.service.MessagePoller;

@SpringBootApplication
public class Day25lCombinedApplication implements CommandLineRunner{

	// day25 - slide 11
	@Autowired
	private MessagePoller messagePoller;
	
	public static void main(String[] args) {
		SpringApplication.run(Day25lCombinedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		messagePoller.start();
	}

}
