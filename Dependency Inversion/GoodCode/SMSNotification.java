public class SMSNotification implements NotificationSender{
    @Override
    public void sendNotification(String messsage) {
        System.out.println(messsage);
    }
}
