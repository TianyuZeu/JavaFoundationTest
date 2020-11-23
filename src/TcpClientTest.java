import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class TcpClientTest {


    public static void main(String[] args) {

        Socket socket =  null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            //创建socket连接
            int port = 9999;
            socket = new Socket(serverIP,port);
            //发送消息
            os = socket.getOutputStream();
            fis = new FileInputStream("E:\\使用手册\\JavaScript.chm");
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null) fis.close();
                if(os!=null) os.close();
                if(socket!=null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
