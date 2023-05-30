import java.math.BigDecimal;

public class TransactionOperations {
    public void deposit(BigDecimal amount, int accountNumber){
        //Getting account details it is job of account operations
        AccountOperations accountOperations = new AccountOperations();
        Account account = new Account();
        account.setTotalAmount(account.getTotalAmount().add(amount));
    }

    public void withdraw(BigDecimal amount, int accountNumber){
        AccountOperations accountOperations = new AccountOperations();
        Account account = accountOperations.geAccount(accountNumber);
        account.setTotalAmount(account.getTotalAmount().subtract(amount, null));
    }
}
