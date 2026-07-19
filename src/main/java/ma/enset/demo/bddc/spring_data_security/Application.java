package ma.enset.demo.bddc.spring_data_security;

import jakarta.transaction.Transactional;
import ma.enset.demo.bddc.spring_data_security.entities.*;
import ma.enset.demo.bddc.spring_data_security.repository.AccountOperationRepository;
import ma.enset.demo.bddc.spring_data_security.repository.BankAccountRepository;
import ma.enset.demo.bddc.spring_data_security.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner start(CustomerRepository customerRepository , AccountOperationRepository operationRepository, BankAccountRepository bankAccountRepository){
	return args ->{



 };

	}

}

