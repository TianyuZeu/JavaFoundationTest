import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
            this.datagramSocket = new DatagramSocket(fromPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

            try {
                while(true){
                    String data = bufferedReader.readLine();
                    byte[] buff = data.getBytes();
                    datagramPacket = new DatagramPacket(buff,buff.length,new InetSocketAddress("localhost",9999));
                    datagramSocket.send(datagramPacket);
                    if(data == "bye")
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bufferedReader.close();
                    datagramSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }







    public int getFromPort() {
        return fromPort;
    }

    public String getToIP() {
        return toIP;
    }

    public int getToPort() {
        return toPort;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setFromPort(int fromPort) {
        this.fromPort = fromPort;
    }

    public void setToIP(String toIP) {
        this.toIP = toIP;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public void setDatagramSocket(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }
}

