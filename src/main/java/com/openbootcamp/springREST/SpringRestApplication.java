package com.openbootcamp.springREST;

import com.openbootcamp.springREST.entities.Laptop;
import com.openbootcamp.springREST.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class) ;

		Laptop laptop1 = new Laptop(null,"Lenovo","modelito","SSD",16,"HD");
		Laptop laptop2 = new Laptop(null,"HP","Modelo2","SSD",8,"FullHD");

		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
	}

}
