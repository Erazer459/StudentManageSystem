package StudentManageSystem;
import StudentManageSystem.StudentTask.StudentTasks;
import StudentManageSystem.StudentTask.StudentTasksImp;

import java.io.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//需要用到专业码时需要调用这个类
public class MajorNum {
    private static File file=new File("majornum");
    private static ConcurrentHashMap<String, String> nums;
    public static boolean saveNumstoFile(ConcurrentHashMap<String,String>Nums){
        try{//创建文件输出流对象,以及异常捕获块
            FileOutputStream fout= new FileOutputStream(file);
            //创建缓冲区输出流对象，BufferedOutputStream自带一个8192大小的Byte数组，它一次可以从数据源中读取8192个字节，效率非常高。
            BufferedOutputStream bout=new BufferedOutputStream(fout);
            //创建对象输出流
            ObjectOutputStream obout=new ObjectOutputStream(bout);
            obout.writeObject(Nums);
            obout.flush();
            obout.close();
            System.out.println("专业码写入文件成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
public static Map<String,String> getNumsfromFile(){
    // 判断要读取的文件是否存在
    if(!file.exists()&&file.length()==0){
        System.out.println("专业码文件尚未建立，请先初始化专业码文件！");
        return  null;
    }
    try{
        //创建文件输入流对象
        FileInputStream fin=new FileInputStream(file);
        //创建缓冲区输入流对象,用来加速读取文件字节
        BufferedInputStream bin=new BufferedInputStream(fin);
        //创建对象输入流
        ObjectInputStream objin=new ObjectInputStream(bin);
        //返回输入流对象
        return (Map<String,String>) objin.readObject();
    } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
    }
    return  null;
}
public static boolean checkNumKey(String key){//检查专业码是否存在
        nums= (ConcurrentHashMap<String, String>) getNumsfromFile();
    if(nums.containsKey(key)){
            return true;
        }else{
            return  false;
        }
}
public static boolean checkNumValue(String value){//确保模糊查询的专业存在
        nums=(ConcurrentHashMap<String, String>) getNumsfromFile();
        for(String vvalue:nums.values()){
            if(StudentTasksImp.unclearMatch(vvalue, value)){
                return true;
            }
        }
        return false;
}
}

