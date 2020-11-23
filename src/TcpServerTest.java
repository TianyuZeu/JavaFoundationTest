import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServerTest {



    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            //服务器地址：
            serverSocket = new ServerSocket(9999);
            //等待Client连接
            socket = serverSocket.accept();
            //读取客户端消息
            is = socket.getInputStream();
            fos = new FileOutputStream("E:\\JavaScript.chm");
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fos!=null) fos.close();
                if(is!=null) is.close();
                if(socket!=null) socket.close();
                if(serverSocket!=null)serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}