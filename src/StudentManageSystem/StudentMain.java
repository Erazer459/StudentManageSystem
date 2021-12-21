package StudentManageSystem;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class StudentMain {
    public static void main(String[] args){//初始化专业序号，将其存入一个HashMap中，并保存进majornum
        ConcurrentHashMap<String,String>nums=new ConcurrentHashMap<String,String>();//使用ConcurrentHashMap确保线程安全
        nums.put("0001","信息系统与信息管理");
        nums.put("0002","物联网工程");
        nums.put("0003","软件工程");
        nums.put("0004","信息与计算科学");
        nums.put("0005","数据科学与大数据技术");
        nums.put("0006","电子信息工程");
        MajorNum.saveNumstoFile(nums);//将专业码HashMap写入文件中
        FileOutputStream fout = null;
        ObjectOutputStream obout = null;
        /**
         * 初始化file中的学生集合
         */
        try{//创建文件输出流对象,以及异常捕获块
            fout= new FileOutputStream("stu.bin");

            //创建对象输出流
            obout=new ObjectOutputStream(fout);
            obout.writeObject(new ArrayList<Student>().add(new Student("zhangsna","2020 0001 4 1 23","男","软件","1311190@qq.com")));//new ArrayList<Student>()
            obout.flush();//清空缓冲区

            System.out.println("学生集合写入文件成功！");
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
        StudentMenu smenu=new StudentMenu();
        smenu.menu();
    }
}
