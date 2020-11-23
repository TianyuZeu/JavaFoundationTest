import java.io.*;
import java.sql.SQLOutput;

public class CharacterStreamTest {

    //访问文件 FileReader FileWriter
    private void FileRw(){

        FileWriter fw = null;
        FileReader fr = null;
        try{
            fw = new FileWriter("E:\\test\\file.txt");
            fr = new FileReader("E:\\test\\file.txt");
            fw.write("file reader writer : newtouchchchchchchchh");
            fw.flush();
            char[] c = new char[32];
            int i = 0;
            while((i = fr.read(c))!= - 1){
                String str = new String(c,0,i);
                System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }  finally {
            try {
                fw.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //缓冲流 buffered rw
    private void BufferedRw(){

        BufferedWriter bw = null;
        BufferedReader br = null;
        try{
            bw = new BufferedWriter(new FileWriter("E:\\test\\buf.txt"));
            br = new BufferedReader(new FileReader("E:\\test\\buf.txt"));
            bw.write("buffered reader writer : newtouchooooo");
            bw.flush();
            char[] c = new char[32];
            int i = 0;
            while((i = br.read(c))!= - 1){
                String str = new String(c,0,i);
                System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }  finally {
            try {
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //charArray rw
    private void charArrayRw(){

        char[] c = {'n','e','w','t','o','u','c','h'};
        CharArrayWriter cw = null;
        CharArrayReader cr = null;
        try{
            cw = new CharArrayWriter();
            cw.write(c,0,8);
            char[] write = cw.toCharArray();
            String str = new String(write);
            System.out.println(str);

            cr = new CharArrayReader(c);
            char[] read  = new char[32];
            int i = 0;
            while((i = cr.read(read))!=-1){
                String str1 = new String(read,0,i);
                System.out.println(str1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            cw.close();
            cr.close();
        }
    }

    //StringReader writer
    private void stringRw(){

        String str = "new touch";
        StringWriter sw = null;
        StringReader sr = null;
        try{
            sw = new StringWriter();
            sw.write(str);
            System.out.println(str);

            sr = new StringReader(str);
            char[] c = new char[32];
            int i = 0;
            while((i = sr.read(c,0,i))!=-1){
                String str1 = new String(c,0,i);
                System.out.println(str1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                sw.close();
                sr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }









    public static void main(String[] args) {
        CharacterStreamTest characterStreamTest = new CharacterStreamTest();

        //访问文件 FileReader FileWriter
        //characterStreamTest.FileRw();

        //缓冲流 buffered rw
        //characterStreamTest.BufferedRw();

        //charArray rw
        //characterStreamTest.charArrayRw();

        //String rw
        characterStreamTest.stringRw();
    }
}
