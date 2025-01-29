package com.split.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.split"})//entity'leri görmeyi sağlar
@ComponentScan(basePackages = {"com.split"})// restcontroller,service,repository gibi yapıların spring containerda bean'ini oluşturur
@EnableJpaRepositories(basePackages = {"com.split"})//JpaRepository'i aktifleştirdi
@SpringBootApplication
public class EmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}

}
