package com.mock.ncb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class NcbApplication {

	public static void main(String[] args) {
		SpringApplication.run(NcbApplication.class, args);
		Double test = 1.0;

		double test2 = 2.0;
		pepe(test2);
		Random r = new Random();
		r.nextDouble();
		System.out.println(test + test2);
		System.out.println("jojojo");
	}

	private static void pepe(Double d){

	}

}
