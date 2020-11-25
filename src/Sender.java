import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Sender implements Runnable {

    private int fromPort;
    private String toIP;
    private int toPort;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private BufferedReader bufferedReader;

    public Sender(int fromPort,String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            this.datagramSocket = new DatagramSocket(fromPort);//用哪一个端口发
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            try {
                String data = bufferedReader.readLine();
                byte[] buff = data.getBytes();
                datagramPacket = new DatagramPacket(buff,buff.length,new InetSocketAddress(this.toIP,this.toPort));//服务器地址 端口
                datagramSocket.send(datagramPacket);
                if(data.equals("bye"))
                    flag = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

