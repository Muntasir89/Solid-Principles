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

</br>***AccountOperations.java***
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
</br> ***TransactionOperations.java***
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
</br> ***Main.java***
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
</br> ***BadCalculator.java***
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
***Operations.java***
```java
public interface Operations {
    public int perform(int number1, int number2);
}
```
***AddOperation.java***
```java
public class AddOperation implements Operations{
    @Override
    public int perform(int number1, int number2) {
        return number1 + number2;
    }
}
```
***SubtractOperations.java***
```java
public class SubtractOperations implements Operations{

    @Override
    public int perform(int number1, int number2) {
        return number1 - number2;
    }
}
```
</br> ***Calculator.java***
```java
public class Calculator {
    public int calculateNumber(int number1, int number2, Operations operation){
        return operation.perform(number1, number2);
    }
}
```
Now If we want to add a new operations like division we have to just create a new class implementing the operations.
## Liskov Substitution
This principles states that when you are creating a parent child relationship. 
</br> For example,
</br> ***LoanPayment.java***
```java
public interface LoanPayment {
    public void doPayment(int amount);
    public void forceCloseLoan();
    public void doRepayment(int amount);
}
```
</br> ***HomeLoan.java***
```java
public class HomeLoan implements LoanPayment{
    @Override
    public void doPayment(int amount){

    }
    @Override
    public void forceCloseLoan(){

    }
    @Override
    public void doRepayment(int amount){
        
    }
}
```
</br> ***CreditCardLoan.java***
```java
public class CreditCardLoan  implements LoanPayment{
    @Override
    public void doPayment(int amount) {
        
    }

    @Override
    public void forceCloseLoan() {
        throw new UnsupportedOperationException("Unimplemented method 'forceCloseLoan'");
    }

    @Override
    public void doRepayment(int amount) {
        
    }
}
```
</br> ***LoanClosureService.java***
```java
public class LoanClosureService {
    private LoanPayment loanPayment;

    public LoanClosureService(LoanPayment loanPayment){
        this.loanPayment = loanPayment;
    }

    public void forceCloseLoan(){
        loanPayment.forceCloseLoan();
    }
}

```
In the LoanClosureService when we pass the ***loanPayment*** as **CreditCardLoan** and we want to forcecloseLoan then it will not be accepted/unsupported.

Now if we change the code like this.
</br> ***LoanPayment.java***
```java
public interface LoanPayment {
    public void doPayment(int amount);
}
```
</br> ***HomeLoan.java***
```java
public class HomeLoan implements SecureLoan{
    @Override
    public void doPayment(int amount) {

    }

    @Override
    public void forceCloseLoan() {
        
    }
}
```
```</br> ***CreditCarddLoan.java***
```java
public class CreditCarddLoan implements LoanPayment{
    @Override
    public void doPayment(int amount) {
        
    }
}
```
</br> ***LoanClosureService.java***
```java
public class LoanClosureService {
    private SecureLoan secureLoan;

    public LoanClosureService(SecureLoan secureLoan){
        this.secureLoan = secureLoan;
    }

    public void forceCloseLoan(){
        secureLoan.forceCloseLoan();
    }
}
```
</br> ***SecureLoan.java***
```java
public interface SecureLoan extends LoanPayment{
    public void forceCloseLoan();
}
```
Now the forceCloseLoan() method can be implemented for secureLoan without any trouble.
## Interface Segregation
We should create a interface in such a way that emphasizes designing fine-grained interfaces that are specific to the needs of the clients. It states that clients should not be forcesd to depend on interfaces they do not use.
In short, we should implement the interface in such a way that all the method should relevant to the interface. </br>
If we want to implement an interface with much more method like this...
</br> ***BadDAOInterface.java***
```java
public interface BadDAOInterface{
    public void openConnection();
    public void createRecord();
    public void openFile();
    public void deleteRecord();
}
```
***BadDBDaoConnection.java***
```java
public class BadDBDaoConnection implements BadDAOInterface{
    @Override
    public void openConnection() {
        
    }
    @Override
    public void createRecord() {
        
    }
    @Override
    public void openFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openFile'");
    }
    @Override
    public void deleteRecord() {
        
    }
}
```
Here ***openFile()*** method is not appropriate for a database connection. But User have to implement this. Which is a **Bad** implementation.
Now if we break this into some ***interface*** then the code will look like this.
</br> ***DAOInterface.java***
```java
public interface DAOInterface {
    public void createRecord();
    public void deleteRecord();
}
```
***DBInterface.java***
```java
public interface DBInterface {
    public void openConnection();
}
```
***FileInterface.java***
```java
public interface FileInterface {
    public void openFile();
}
```
***DBDaoConnection.java***
```java
public class DBDaoConnection implements DAOInterface, DBInterface{
    @Override
    public void openConnection() {
        
    }
    @Override
    public void createRecord() {
        
    }
    @Override
    public void deleteRecord() {

    }
}
```
***FileDaoConnection.java***
```java
public class FileDaoConnection implements FileInterface, DAOInterface{
    @Override
    public void createRecord() {
        
    }
    @Override
    public void deleteRecord() {
        
    }
    @Override
    public void openFile() {
        
    }
}
```
## Dependency Inversion
Higher level modules does not have the dependent on lower level modules. Instead, both should depend on abstractions.</br></br>
In simpler terms, the principle suggests that classes should depend on abstractions (interfaces or abstract classes) rather than concrete implementations. This allows for more flexibility, extensibility, and easier testing.
- ***High-level modules:*** These are modules or classes that define high-level policies or orchestrate the overall behavior of the system. They should not directly depend on the low-level implementation details.
- ***Low-level modules:*** These are modules or classes that handle specific implementation details or lower-level functionality. They should not define policies but rather adhere to them.
</br></br>***Customer.java***
```java
public class Customer {
    private SMSNotificationService smsnotificationService;

    public Customer(){
        smsnotificationService = new SMSNotificationService();
    }
}
```
***NotificationService.java***
```java
public class SMSNotificationService {
    public void sendNotification(String message){
        System.out.println(message);
    }
}
```
Now If a person buys something then he/she will get notification.</br>
So, Person class will like this.
</br>***Person.java***
```java
public class Person {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.purchaseItem();
    }
}
```
Output:
```
Thank you for your purchase.
```
But if want to add new notification way like Email notification then we have to add a new class as well as customer code will change  like this.
</br> ***Customer.java***
```java
public class Customer {
    private SMSNotificationService smsnotificationService;
    private EmailNotificationService emailNotificationService;

    public Customer(){
        smsnotificationService = new SMSNotificationService();
        emailNotificationService = new EmailNotificationService();
    }
    public void purchaseItem(){
        //business logic
        //smsnotificationService.sendNotification("SMS: Thank you for your purchase.");
        emailNotificationService.sendNotification("Email: Thank you for your purchase.");
    }
}
```
***EmailNotificationService.java***
```java
public class EmailNotificationService{
    public void sendNotification(String message){
        System.out.println(message);
    }
}
```
Output:
```
Email: Thank you for your purchase.
```
Did you get the difficulty??? 🤔🤔🤔🤔
</br> We can handle this trouble situation with **Dependency Inversion Principle** very efficiently. Let's see...</br>
***NotificationSender.java***
```java
public interface NotificationSender{
    void sendNotification(String messsage);
}
```
***Customer.java***
```java
public class Customer {
    private NotificationSender notificationSender;

    public Customer(NotificationSender notificationSender){
        this.notificationSender = notificationSender;
    }
    public void purchaseItem(){
        notificationSender.sendNotification("Thank you for your purchase");
    }
}
```
***SMSNotification.java***
```java
public class SMSNotification implements NotificationSender{
    @Override
    public void sendNotification(String messsage) {
        System.out.println(messsage);
    }
}
```
***EmailNotification.java***
```java
public class EmailNotification implements NotificationSender{
    @Override
    public void sendNotification(String messsage) {
        System.out.println(messsage);
    }
}
```
***Person.java***
```java
public class Person {
    public static void main(String[] args) {
        NotificationSender notificationSender = new SMSNotification();
        Customer customer = new Customer(notificationSender);
        customer.purchaseItem();
    }
}
```
Output:
```
Thank you for your purchase
```
Now if want to send notification by Email just change ***SMSNotification()*** by ***EmailNotification()***. Easy right??? </br>
Again, If we want to add new notification by using WhatsApp just add this class. create object ***WhatsAppNotification()***
</br>***WhatsAppNotification.java***
```java
public class WhatsAppNotification implements NotificationSender{
    @Override
    public void sendNotification(String messsage) {
        System.out.println(messsage);
    }
}
```