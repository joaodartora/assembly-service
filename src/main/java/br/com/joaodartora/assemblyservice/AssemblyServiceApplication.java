package br.com.joaodartora.assemblyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AssemblyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssemblyServiceApplication.class, args);
	}

}
