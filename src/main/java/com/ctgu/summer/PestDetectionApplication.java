package com.ctgu.summer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ctgu.summer.mapper")
public class PestDetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PestDetectionApplication.class, args);
		System.out.println("http://localhost:8088/swagger-ui.html");
	}

}
