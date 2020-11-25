public class Customer {
    public static void main(String[] args) {
        new Thread(new Sender(6666,"localhost",7777)).start();
        new Thread(new Receiver(8888,"李顾问")).start();
    }

}
