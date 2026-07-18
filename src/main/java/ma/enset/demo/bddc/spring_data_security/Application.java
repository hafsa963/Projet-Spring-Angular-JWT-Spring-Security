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

		Stream.of("hassan","yassine","hafsa").forEach(
				name -> {
					Customer customer = new Customer();
					customer.setName(name);
					customer.setEmail(name+ "@gmail.com");
					customerRepository.save(customer);
				});
		customerRepository.findAll().forEach(
				cust ->{
					CurrentAccount currentAccount = new CurrentAccount();
					currentAccount.setBalance(Math.random()*9000);
					currentAccount.setCreatedAt(new Date());
					currentAccount.setStatus(AccountStatus.CREATED);
					currentAccount.setCustomer(cust);
					currentAccount.setOverDraft(9000);
					bankAccountRepository.save(currentAccount);

					SavingAccount savingAccount = new SavingAccount();
					savingAccount.setBalance(Math.random()*9000);
					savingAccount.setCreatedAt(new Date());
					savingAccount.setStatus(AccountStatus.CREATED);
					savingAccount.setCustomer(cust);
					savingAccount.setInsertRate(5.5);
					bankAccountRepository.save(savingAccount);

					bankAccountRepository.findAll().forEach(acc->{
						for(int i = 0;i<5;i++) {

							AccountOperation accountOperation = new AccountOperation();
							accountOperation.setOperationDate(new Date());
							accountOperation.setAmounnt(Math.random()*12000);
							accountOperation.setType(Math.random()>0.5? OperationType.Debit : OperationType.Credit);
							accountOperation.setBankAccount(acc);
							operationRepository.save(accountOperation);
						}


					});
				});
 };

	}

}

