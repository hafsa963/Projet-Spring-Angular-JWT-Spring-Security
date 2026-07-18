package ma.enset.demo.bddc.spring_data_security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@Data
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Date operationDate;
  private double amounnt;
  private OperationType type;
  @ManyToOne
  private BankAccount  bankAccount;
}
