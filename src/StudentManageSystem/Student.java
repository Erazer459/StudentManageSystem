package StudentManageSystem;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
//创建一个Student类，里面包含了所有信息的get和set方法
public class Student implements Serializable{
    private String sname;
    private String sid;
    private String sex;
    private String classname;
    private String email;
    private String firtime;
    private String lstime;
public Student (){
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
    firtime=formatter.format(date);//一个Student对象被建立的时候获取信息建立时间
}//使用构造函数对Student进行初始化
public Student (String sname,String sid,String sex,String classname,String email){
    super ();
    this.sname=sname;
    this.sid=sid;
    this.sex=sex;
    this.classname=classname;
    this.email=email;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
     Date date = new Date(System.currentTimeMillis());
     firtime=formatter.format(date);//获取信息建立时间
     lstime=formatter.format(date);//获取最后修改时间
}
public String getSid(){
    return sid;
}
public void setSid(String sid){
    this.sid=sid;
}
public String getSname(){
    return sname;
}
public  void setSname(String sname){
    this.sname=sname;}
public String getSex(){
    return sex;
}
public void setSex(String sex){
    this.sex=sex;
}
public String getEmail() {
    return email;
}
public void setEmail(String email){
    this.email=email;
    }
    public String getClassname(){
    return classname;
    }
    public void setClassname(String classname){
    this.classname=classname;
    }
    public String getFirtime(){
    return firtime;
    }
    public void setLstime(){//修改学生信息时调用此方法来修改最后修改时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date fate = new Date(System.currentTimeMillis());
        this.lstime=formatter.format((fate));
    }
    public String getLstime(){
    return lstime;
    }
}
