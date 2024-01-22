package com.digytal.control;

import com.digytal.control.job.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ControlApiApplication {
	//https://zetcode.com/springboot/bean/

	public static void main(String[] args) {
		ConfigurableApplicationContext configContext= SpringApplication.run(ControlApiApplication.class, args);
	}

@Bean
public CommandLineRunner run(Job job) throws Exception {
	return args -> {
		System.out.println("SE PRECISAR EXECUTAR ALGO NO CONSOLE");
		//j.compensarParcelas();
	};
}
}
