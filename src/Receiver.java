import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver implements Runnable  {

    private int recPort;
    private String mesgFrom;
    private DatagramSocket  datagramSocket = null;

    public Receiver(int recPort, String mesgFrom) {
        this.recPort = recPort;
        this.mesgFrom = mesgFrom;
        try {
            this.datagramSocket = new DatagramSocket(recPort);//用（从）哪一个端口收
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] receiver = null;
        DatagramPacket datagramPacket = null;
        boolean flag = true;
        while(flag){
            try {
                receiver = new byte[1024];
                datagramPacket = new DatagramPacket(receiver,0,receiver.length);
                datagramSocket.receive(datagramPacket);
                byte[] recData = datagramPacket.getData();
                String recData_s = new String(recData,0,recData.length);
                System.out.println(mesgFrom+":"+recData_s);
                if(recData_s.equals("bye"))
                    flag = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
    }
}
