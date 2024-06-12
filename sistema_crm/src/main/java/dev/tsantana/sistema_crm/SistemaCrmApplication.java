package dev.tsantana.sistema_crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SistemaCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaCrmApplication.class, args);
	}

}
