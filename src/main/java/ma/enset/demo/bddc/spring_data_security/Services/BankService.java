package ma.enset.demo.bddc.spring_data_security.Services;

import jakarta.transaction.Transactional;
import ma.enset.demo.bddc.spring_data_security.entities.BankAccount;
import ma.enset.demo.bddc.spring_data_security.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter() {
        Optional<BankAccount> optional = bankAccountRepository.findById(currentAccount.getId());

        if (optional.isPresent()) {
            BankAccount bankAccount = optional.get();

            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
        }
    }


}
