package ma.enset.demo.bddc.spring_data_security.repository;

import ma.enset.demo.bddc.spring_data_security.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer ,Long> {
}
