package com.liang.argorithm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.liang.argorithm.aboutproject")
public class ArgorithmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArgorithmApplication.class, args);
	}

}
