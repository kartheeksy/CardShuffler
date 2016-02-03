package com.cardShuffler.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class has all the default configuration required for the Application to start up ,
 * Creating beans , wiring them , scanning repositories , getting DB Connection etc .
 * @author KARTHEEK YS
 *
 */
@Controller
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = {"com.interview"})
@EntityScan(basePackages = {"com.interview.domain"})
@EnableJpaRepositories( basePackages = {"com.interview.repository"} )
@EnableTransactionManagement
@SpringBootApplication
public class Main {
		
		/**
		 * Loads the SpringBoot Application 
		 * @param args
		 * @throws Exception
		 */
	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(Main.class, args);
	    }
}
