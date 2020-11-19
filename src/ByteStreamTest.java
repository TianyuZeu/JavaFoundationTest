import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

//字节流样例程序
public class ByteStreamTest {

    //访问文件 fileInputStream fileOutputStream
    private void FileIOStream() {
        try(FileInputStream fis = new FileInputStream("E:\\test\\fostest.txt");
//            FileOutputStream fos = new FileOutputStream("E:\\test\\fostest.txt")){
//            int i;
//            while((i = fis.read())!= -1){//Q:why is variable i is necessary.
//            fos.write(i);
//            }
//            System.out.println("FileInputStream FileoutStream 测试成功");

            FileOutputStream fos = new FileOutputStream("E:\\test\\fostest.txt")){
            byte[] b = {0x6e,0x65,0x77,0x74,0x6f,0x75,0x63,0x68};
            fos.write(b);
            int i;
            while((i = fis.read())!= -1){//Q:why is variable i is necessary.
                System.out.println((char)i);
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

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("E:\\test\\biostest.txt"));
            bos = new BufferedOutputStream(new FileOutputStream("E:\\test\\biostest.txt"));
            byte[] b = {0x6e,0x65,0x77,0x74,0x6f,0x75,0x63,0x68};
            bos.write(b);
            bos.flush();//close也会flush，是否直接可以close呢
            int i;
            int j = -1;
            byte[] bb = new byte[16];
            while((i = bis.read(bb)) != -1){
                String chunk = new String(bb,0,i);
                System.out.println(chunk);
            }
            //System.out.println(Arrays.toString(bb));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
               // bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void ObjectIOStream(){
        //Object I/O Stream
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\test\\oiostest.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\test\\oiostest.txt"))){
           Newtouch newtouchOut = new Newtouch("newtouch",26,true);
           oos.writeObject(newtouchOut);
           Newtouch newtouchIn = (Newtouch) ois.readObject();
            System.out.println("读娶到的对象: "+newtouchIn.toString());
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void ByteArrayIOStream(){
        //ByteArray I/O Stream
        byte[] b = {0x6e,0x65,0x77,0x74,0x6f,0x75,0x63,0x68};
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayInputStream bis = new ByteArrayInputStream(b)){

            byte[] out = new byte[32];
            bos.write(b,0,8);
            out = bos.toByteArray();
            for (int i = 0;i<out.length;i++) {
                System.out.print((char)out[i]);
            }
            bos.flush();
            System.out.println("\n====风歌线=====");
            byte[] in = new byte[32];
            int len = 0;
            while((len = bis.read(in))!=-1){
                String str = new String(in,0,len);
                System.out.println(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //piped  thread





    public static void main(String[] args) throws IOException {

        ByteStreamTest bst = new ByteStreamTest();

        //访问文件 fileInputStream fileOutputStream
        //bst.FileIOStream();

        //基本类型的二进制存取 DatInputStream DataInputStream
       // bst.DateIOStream();

        //缓冲 BufferedInputStream BufferedOutputStream
        //bst.BufferedIOStreamTest();

        //对象  ObjectIOStream
        //bst.ObjectIOStream();

        //ByteArray I/O Stream
        bst.ByteArrayIOStream();
    }

}




class Newtouch implements Serializable {
    private String s;
    private int i;
    private boolean b;

    public Newtouch(String s, int i, boolean b) {
        this.s = s;
        this.i = i;
        this.b = b;
    }


    public String toString(){
        return this.getS()+"+"+this.getI()+"+"+this.isB();
    }

    public String getS() {
        return s;
    }

    public int getI() {
        return i;
    }

    public boolean isB() {
        return b;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setB(boolean b) {
        this.b = b;
    }


}
