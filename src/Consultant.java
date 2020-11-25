public class Consultant {
    public static void main(String[] args) {
        new Thread(new Sender(9999,"localhost",8888)).start();
        new Thread(new Receiver(7777,"张先生")).start();
    }
}
