package ma.enset.demo.bddc.spring_data_security.repository;

import ma.enset.demo.bddc.spring_data_security.entities.AccountOperation;
import ma.enset.demo.bddc.spring_data_security.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
