package br.com.fiap.restaurantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//eduardojacobsenguerra@outlook.com
//Juventude@Papo123
@SpringBootApplication
public class RestauranteApplication implements CommandLineRunner {
	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
