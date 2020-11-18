import java.io.*;

//字节流样例程序
public class ByteStreamTest {

    //访问文件 fileInputStream fileOutputStream
    private void FileIOStream() {
        try(FileInputStream fis = new FileInputStream("E:\\test\\fistest.txt");
            FileOutputStream fos = new FileOutputStream("E:\\test\\fostest.txt")){
            int i;
            while((i = fis.read())!= -1){//Q:why is variable i is necessary.
            fos.write(i);
            }
            System.out.println("FileInputStream FileoutStream 测试成功");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //基本类型的二进制存取 DatInputStream DataInputStream
    private void DateIOStream() {

        int i = 66;
        double d = 88.88;
        boolean b = true;

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:\\test\\diostest.txt"));
            DataInputStream dis = new DataInputStream(new FileInputStream("E:\\test\\diostest.txt"))){

            dos.writeInt(i);
            dos.writeDouble(d);
            dos.writeBoolean(b);//Q：why disordered characters exits in the txt file

            System.out.println("read:"+dis.readInt());
            System.out.println("read:"+dis.readDouble());
            System.out.println("read:"+dis.readBoolean());

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //缓冲 BufferedInputStream BufferedOutputStream
    private void BufferedIOStreamTest(){

      //BufferedInputStream



    }





    public static void main(String[] args) throws IOException {

        ByteStreamTest bst = new ByteStreamTest();

        //访问文件 fileInputStream fileOutputStream
        //bst.FileIOStream();

        //基本类型的二进制存取 DatInputStream DataInputStream
        bst.DateIOStream();

    }

}
