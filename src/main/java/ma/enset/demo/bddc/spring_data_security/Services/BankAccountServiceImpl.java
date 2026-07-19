package ma.enset.demo.bddc.spring_data_security.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.demo.bddc.spring_data_security.entities.*;
import ma.enset.demo.bddc.spring_data_security.exception.BalencenoSufficantException;
import ma.enset.demo.bddc.spring_data_security.exception.CostumerNotFundException;
import ma.enset.demo.bddc.spring_data_security.repository.AccountOperationRepository;
import ma.enset.demo.bddc.spring_data_security.repository.BankAccountRepository;
import ma.enset.demo.bddc.spring_data_security.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class BankAccountServiceImpl implements BankAccountService{

   //Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BankAccountRepository  bankAccountRepository;
    @Autowired
    private AccountOperationRepository accountOperationRepository;

    @Override
    public Customer saveCostomer(Customer costomer) {
        log.info("saving new customer ");
        Customer savedCostomer = customerRepository.save(costomer);
        return  savedCostomer;

    }

    @Override
    public CurrentAccount SavecurrentBackAccount(double balance, double overDraft, String type, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null)
            throw new CostumerNotFundException("Costummer not found");

       CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(Long.parseLong(UUID.randomUUID().toString()));
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(balance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount saveCurrentAccount = bankAccountRepository.save(currentAccount);

        return saveCurrentAccount;
    }

    @Override
    public SavingAccount SaveSavingBankAccount(double balance, double insertRate, double overDraft, String type, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null)
            throw new CostumerNotFundException("Costummer not found");

        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setId(Long.parseLong(UUID.randomUUID().toString()));
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(balance);
        savingAccount.setCustomer(customer);
        savingAccount.setInsertRate(insertRate);
        SavingAccount savedavingaccount = bankAccountRepository.save(savingAccount);



        return savedavingaccount;
    }


    @Override
    public List<Customer> listCostomers() {
        log.info("Listing all customers");
        return customerRepository.findAll();
    }

    @Override
    public BankAccount getBanckAccount(String accountid) {
        log.info("Searching account {}", accountid);

          BankAccount bankAccount = bankAccountRepository.findById(accountid)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
        return  bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BalencenoSufficantException{
        log.info("Debit of {} DH from account {}");
        BankAccount bankAccount =getBanckAccount(accountId);
        if(bankAccount.getBalance() < amount)
            throw new BalencenoSufficantException("balence no suffisant");
        AccountOperation accountOperation = new AccountOperation();
        ;
        accountOperation.setType(OperationType.Debit);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmounnt(amount);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        accountOperationRepository.save(accountOperation);




    }

    @Override
    public void credit(String accountId, double amount, String description) {
        log.info("Debit of {} DH from account {}");
        BankAccount bankAccount =getBanckAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();
        ;
        accountOperation.setType(OperationType.Credit);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmounnt(amount);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        bankAccount.setBalance(bankAccount.getBalance()+ amount);
        accountOperationRepository.save(accountOperation);

    }

    @Override
    public void Transfer(String accountIdsource, String accountIdDestination, double amount) throws CostumerNotFundException, BalencenoSufficantException {

        log.info("Transfer of {} DH from {} to {}",
                amount, accountIdsource, accountIdDestination);

        debit(accountIdsource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdsource);

    }
}
