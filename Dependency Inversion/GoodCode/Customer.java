public class Customer {
    private NotificationSender notificationSender;

    public Customer(NotificationSender notificationSender){
        this.notificationSender = notificationSender;
    }
    public void purchaseItem(){
        notificationSender.sendNotification("Thank you for your purchase");
    }
}
