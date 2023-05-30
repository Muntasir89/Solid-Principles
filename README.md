# Solid-Principles
Solid Design Pattern is really important to learn as a software developer. Whenever you are writing your code you should always follow the solid design pattern. It will allow you to create a very robust code and really scalable code as well. If you follow the **SOLID PRINCIPLE** you will be able to improve your code readability and code structure.
## Single Responsibility
**Whatever classes that you create should have only one responsiblity. And that responsibility only should follow by that particular class.**
</br>And if you encounter 👉👉**AND**👈👈 that is not maintaining our single responsibility. </br>
**Account.java**</br>
```java
public class Account{
    private int accountNumber;
    private String firstName;
    private BigDecimal totalAmount;

    public int getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
}
```

</br>***AccountOperations***
```java
public class AccountOperations{
    private static Map<Integer, Account>accountMap = new HashMap<>();

    public void addAccount(Account account){
        accountMap.put(account.getAccountNumber(), account);
    }

    public void updateAccount(Account account){
        accountMap.put(account.getAccountNumber(), account);
    }

    public Account geAccount(int accountNumber){
        return accountMap.get(accountNumber);
    }
}
```
In this ***AccountOperations.java*** only the account related operations. Unlikely, it does not have the deposit operations becuase it does not belong to the account operations.
</br> ***TransactionOperations***
```java
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
```
In this ***TransactionsOperations*** only can perform the deposit and withdraw operations.
</br> ***Main***
```java
public class Main{
    public static void main(String args[]){
        Account account = new Account();
        account.setAccountNumber(123);
        
        account.setFirstName("Muntasir");
        account.setTotalAmount(BigDecimal.valueOf(100000));
        System.out.println(account);

        AccountOperations accountOperations = new AccountOperations();
        accountOperations.addAccount(account);

        TransactionOperations transactionOperations = new TransactionOperations();
        transactionOperations.deposit(BigDecimal.valueOf(123), 123);
        System.out.println(account.getTotalAmount());
    }
}
```

## OCP Principles
Open for extension and closed for modification. Any class that you create should open for extension and closed for modification.

```java
public class BadCalculator{
    public int calculateNumber(int number1, int number2, String type){
        int result = 0;
        switch(type){
            case "sum":
                result = number1 + number2;
            case "sub":
                result = number1 + number2;
        }
        return result;
    }
}
```
If we want to add a new method like division or multiplication we have to modify the code. It is a violation of our ***OCP*** principles.</br>
Now let's have a look at ***good*** code.</br>
***Operations***
```java
public interface Operations {
    public int perform(int number1, int number2);
}
```
</br> ***AddOperation***
```java
public class AddOperation implements Operations{
    @Override
    public int perform(int number1, int number2) {
        return number1 + number2;
    }
}
```
</br> ***SubtractOperations***
```java
public class SubtractOperations implements Operations{

    @Override
    public int perform(int number1, int number2) {
        return number1 - number2;
    }
}
```
</br> ***Calculator***
```java
public class Calculator {
    public int calculateNumber(int number1, int number2, Operations operation){
        return operation.perform(number1, number2);
    }
}
```
Now If we want to add a new operations like division we have to just create a new class implementing the operations.
