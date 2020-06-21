package com.tweltar.TrelloClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrelloCloneApplication {

	public static void main(String[] args) {	
		SpringApplication.run(TrelloCloneApplication.class, args);
	}
// http://localhost:8080/trelloClone/v1/
}
