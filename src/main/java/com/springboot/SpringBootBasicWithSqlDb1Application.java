package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@SpringBootApplication
@EnableScheduling
public class SpringBootBasicWithSqlDb1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBasicWithSqlDb1Application.class, args);
	}
}
