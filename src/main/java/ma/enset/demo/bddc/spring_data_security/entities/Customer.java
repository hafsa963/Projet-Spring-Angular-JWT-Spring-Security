package ma.enset.demo.bddc.spring_data_security.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
  /*  private double amount;
    private int RIB;*/
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccountList;
}
