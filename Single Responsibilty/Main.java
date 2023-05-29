import java.math.BigDecimal;

public class Main{
    public static void main(String args[]){
        Account account = new Account();
        account.setAccountNumber(123);
        
        account.setFirstName("Muntasir");
        account.setTotalAmount(BigDecimal.valueOf(100000));
        System.out.println(account);

        AccountOperations accountOperations = new AccountOperations();
        accountOperations.addAccount(account);
    }
}