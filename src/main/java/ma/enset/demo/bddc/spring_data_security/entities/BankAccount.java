package ma.enset.demo.bddc.spring_data_security.entities;

import jakarta.persistence.*;
import jdk.dynalink.Operation;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type",length = 4)
@Table(name="BankAccount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BankAccount {
        @Id
        @GeneratedValue
        private long id;
        private double balance;
        private Date CreatedAt;
        @Enumerated(EnumType.STRING)
        private AccountStatus status;
        @ManyToOne
        private Customer customer;
        @OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER)
        private List<AccountOperation> accountOperations;

}
