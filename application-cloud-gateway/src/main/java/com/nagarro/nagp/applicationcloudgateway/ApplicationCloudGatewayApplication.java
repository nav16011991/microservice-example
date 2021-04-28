package com.nagarro.nagp.applicationcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationCloudGatewayApplication.class, args);
	}

}
