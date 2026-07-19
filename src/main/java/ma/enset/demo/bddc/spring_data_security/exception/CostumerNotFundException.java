package ma.enset.demo.bddc.spring_data_security.exception;

public class CostumerNotFundException extends RuntimeException {
    public CostumerNotFundException(String message){
       super(message);

    }
}
