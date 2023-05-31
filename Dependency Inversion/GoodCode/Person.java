public class Person {
    public static void main(String[] args) {
        NotificationSender notificationSender = new SMSNotification();
        Customer customer = new Customer(notificationSender);
        customer.purchaseItem();
    }
}
