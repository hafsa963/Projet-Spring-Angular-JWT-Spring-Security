package ma.enset.demo.bddc.spring_data_security.Services;

import jakarta.transaction.Transactional;
import ma.enset.demo.bddc.spring_data_security.entities.*;
import ma.enset.demo.bddc.spring_data_security.exception.BalencenoSufficantException;
import ma.enset.demo.bddc.spring_data_security.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public interface BankAccountService {


     Customer saveCostomer(Customer costomer);
     CurrentAccount SavecurrentBackAccount(double balance , double overDraft, String type , Long customerId);
     SavingAccount SaveSavingBankAccount(double balance , double insertRate , double overDraft, String type , Long customerId);

     List<Customer> listCostomers();
     BankAccount getBanckAccount(String accountid);
     void debit(String accountId, double amount , String description) throws BalencenoSufficantException;
     void credit (String accountId, double amount , String description);
    void Transfer  (String accountIdsource,String accountIdDestination, double amount  ) throws BalencenoSufficantException;

}
