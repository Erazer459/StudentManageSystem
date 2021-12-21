package StudentManageSystem;
import java.io.*;
import java.util.*;
//创建一个工具类，向文件写入和从文件读取学生集合
public class StudentIOFile {
    static File file=new File("stu.bin");
    /**
     * 向文件中写入学生的集合对象（ArrayList集合）
     * */
    public static boolean saveSListtoFile(ArrayList<Student> SList){
        FileOutputStream fout = null;
        ObjectOutputStream obout = null;
        try{//创建文件输出流对象,以及异常捕获块
             fout= new FileOutputStream(file);
        //创建对象输出流
             obout=new ObjectOutputStream(fout);
            obout.writeObject(SList);//new ArrayList<Student>()
            obout.flush();//清空缓冲区
            System.out.println("学生集合写入文件成功！");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (obout != null)
                obout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fout != null){
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /***
     * 从文档中读取学生集合
     *
     * */
    public static List<Student> getSListfromFile(){
        // 判断要读取的文件是否存在
        FileInputStream fin= null;
        BufferedInputStream bin= null;
        ObjectInputStream objin= null;
        ArrayList<Student> SList= null;
        try {
            //创建文件输入流对象
             fin = new FileInputStream(file);
            //创建对象输入流
            objin = new ObjectInputStream(fin);
            //将输入流的对象传给 SList
            ArrayList<Student> list = (ArrayList<Student>)objin.readObject();
            SList.addAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objin != null) {
                try {
                    objin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            return SList;
    }
}
