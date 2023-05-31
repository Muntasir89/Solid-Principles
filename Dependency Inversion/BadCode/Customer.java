public class Customer {
    private SMSNotificationService smsnotificationService;
    private EmailNotificationService emailNotificationService;

    public Customer(){
        smsnotificationService = new SMSNotificationService();
        emailNotificationService = new EmailNotificationService();
    }
    public void purchaseItem(){
        //smsnotificationService.sendNotification("Thank you for your purchase.");
        emailNotificationService.sendNotification("Thank you for your purchase.");
    }
}

