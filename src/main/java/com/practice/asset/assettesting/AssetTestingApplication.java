package com.practice.asset.assettesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AssetTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetTestingApplication.class, args);
	}

}
