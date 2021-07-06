package com.project.Investment.App;

import com.project.Investment.App.model.Entity;
import com.project.Investment.App.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class InvestmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentAppApplication.class, args);
	}


}
