package com.example.test_runners;

import com.example.test_runners.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.test_runners.run.Location;
import com.example.test_runners.run.Run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class TestRunnersApplication {

	private static final Logger log = LoggerFactory.getLogger(TestRunnersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestRunnersApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(RunRepository runRepository){
//		return  args -> {
//			Run run = new Run(1,"first", LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),5, Location.OUTDOOR);
//			runRepository.create(run);
//		};
//	}

}
