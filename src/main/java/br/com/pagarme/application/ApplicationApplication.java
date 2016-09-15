package br.com.pagarme.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"br.com.pagarme"})
@EnableAutoConfiguration
public class ApplicationApplication {

	public static void main(String[] args){
		SpringApplication.run(ApplicationApplication.class);
	}
}
