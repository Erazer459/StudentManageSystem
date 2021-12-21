package StudentManageSystem.StudentTask;
import StudentManageSystem.MajorNum;
import StudentManageSystem.Student;
import StudentManageSystem.StudentIOFile;
import StudentManageSystem.StudentMenu;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentTasksImp implements StudentTasks{
    Student stut=null;
    private  static ArrayList<Student> SList;
    private static ConcurrentHashMap<String, String> nums;
    @Override
    public void save(Student st) {//从文件中获取学生集合对象
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){//检查集合是否为空，或者不存在
            SList= new ArrayList<>();//集合为空则新建一个集合
            SList.add(st);
        }else{
            SList.add(st);
        }
        boolean flag=StudentIOFile.saveSListtoFile(SList);
        if (flag){
            System.out.println("保存学生信息成功！");
        }else{
            System.out.println("保存学生信息失败！");
        }
    }

    @Override
    public void update(Student st) {//更新学生信息时要同时更新最后修改时间
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息存在，请先添加学生！");
        }
        int index=-1;//学生在集合中的索引
        int i=0;//集合下标
        for (Student stu:SList){
            if(Objects.equals(stu.getSid(), st.getSid())){
                index=i;//找到学生，更新索引
                break;
            }
            i++;
        }
        if(index!=-1){
            st.setLstime();//更新最后修改时间
            SList.set(index,st);
            StudentIOFile.saveSListtoFile(SList);
            System.out.println("学生信息更新成功！");
        }else{
            System.out.println("学生信息更新失败");
        }
    }

    @Override
    public boolean findBySid(String sid) {
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息，请先添加！");
            return false;
        }
        int i=0;
        System.out.println("查找结果如下:");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        for (Student st:SList){
        if(StudentTasksImp.unclearMatch(st.getSid(),sid)){
            System.out.println(st.getSname()+"\t"+st.getSid()+"\t"+st.getSex()+"\t"+st.getClassname()+"t"+st.getEmail()+"\t"+st.getFirtime()+"\t"+st.getLstime());
            i++;
        }
        }
        if(i==0){
            System.out.println("未找到该学号的学生");
            System.out.println("===================================================");
        }
        else {
            System.out.println("===================================================");
            return true;
        }
        return true;
    }

    @Override
    public void delBySid(String sid) {
        //先获取文件中保存的集合对象
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("学生信息不存在，请先添加");
            return;
        }//查找该学生是否存在
        int index=-1;
        int i=0;
        for(Student stu:SList){
            if (Objects.equals(stu.getSid(), sid)){
                index=i;
                break;
            }
            i++;
        }
        if (index!=-1){//找到学生后执行删除
            SList.remove(index);
            StudentIOFile.saveSListtoFile(SList);
            System.out.println("学生信息删除成功！");
        }else{
            System.out.println("学生信息删除失败！");
        }
    }
    @Override
    public  void findByYear(String year) {
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息，请先添加！");
            return ;
        }
        int i=0;
        System.out.println("入学年份"+year+"的学生的信息如下");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        for (Student st:SList){
           String[] arr = st.getSid().split("\\\\s+");
           if(Objects.equals(arr[0], year)){
               System.out.println(st.getSname()+"\t"+st.getSid()+"\t"+st.getSex()+"\t"+st.getClassname()+"t"+st.getEmail()+"\t"+st.getFirtime()+"\t"+st.getLstime());
               i++;
           }
        }
        if(i==0){
            System.out.println("未找到该年份入学的学生");
        }
        System.out.println("===================================================");
    }

    @Override
    public void findBySex(String sex) {
    SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
    if(SList==null||SList.isEmpty()){
        System.out.println("还没有学生信息，请先添加！");
        return;
    }
    int i=0;
        System.out.println("性别为"+sex+"的学生的信息如下");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
    for(Student st:SList){
        if (Objects.equals(st.getSex(), sex)) {
            System.out.println(st.getSname() + "\t" + st.getSid() + "\t" + st.getSex() + "\t" + st.getClassname() + "t" + st.getEmail() + "\t" + st.getFirtime() + "\t" + st.getLstime());
            i++;
        }
    }
    if(i==0){
        System.out.println("未找到该性别的学生");
    }
        System.out.println("===================================================");
    }

    @Override
    public void findByMajor(String major) {
        String key=null;
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        nums=(ConcurrentHashMap<String, String>) MajorNum.getNumsfromFile();
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息，请先添加！");
            return;
        }
        System.out.println("查找结果如下:");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        if (!MajorNum.checkNumValue(major)){
            System.out.println("不存在该专业,请输入正确的专业名称！");
            return;
        }
        for(String vvalue:nums.values()){
            if(StudentTasksImp.unclearMatch(vvalue, major)){
                for(String getKey: nums.keySet()){//获取vvalue对应的key
                    if(nums.get(getKey).equals(vvalue)){
                        key = getKey;
                        for (Student st:SList){
                            String sid=st.getSid();
                            String[] arr = sid.split("\\\\s+");
                            if(Objects.equals(arr[2], key)){
                                System.out.println(st.getSname() + "\t" + st.getSid() + "\t" + st.getSex() + "\t" + st.getClassname() + "t" + st.getEmail() + "\t" + st.getFirtime() + "\t" + st.getLstime());
                            }
                        }
                    }
                }
            }
        }
        System.out.println("===================================================");
    }

    @Override
    public void findByClass(String classname) {
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息，请先添加！");
            return;
        }
        int i=0;
        System.out.println("查找结果如下:");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        for (Student st:SList){
            if(StudentTasksImp.unclearMatch(st.getClassname(),classname)){
                System.out.println(st.getSname()+"\t"+st.getSid()+"\t"+st.getSex()+"\t"+st.getClassname()+"t"+st.getEmail()+"\t"+st.getFirtime()+"\t"+st.getLstime());
                i++;
            }
        }
        if(i==0){
            System.out.println("未找到属于该班级的学生信息");
        }
        System.out.println("===================================================");
    }

    @Override
    public void findBySname(String name) {
        SList=(ArrayList<Student>) StudentIOFile.getSListfromFile();
        if(SList==null||SList.isEmpty()){
            System.out.println("还没有学生信息，请先添加！");
            return;
        }
        int i=0;
        System.out.println("查找结果如下:");
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        for(Student st:SList){
            if (StudentTasksImp.unclearMatch(st.getSname(),name)){
                System.out.println(st.getSname()+"\t"+st.getSid()+"\t"+st.getSex()+"\t"+st.getClassname()+"t"+st.getEmail()+"\t"+st.getFirtime()+"\t"+st.getLstime());
                i++;
            }
        }if(i==0){
            System.out.println("未找到叫该名字的学生");
        }
        System.out.println("===================================================");
    }

    public static boolean unclearMatch(String parent, String child) {//模糊查询模块
        int index = 0;
        return parent.indexOf(child, index) != 0;
    }

    @Override
    public boolean check(String sid) {//为了防止输入id时重复，写一个check方法来检查重复
        SList=(ArrayList<Student>)StudentIOFile.getSListfromFile();
        for(Student st : SList){
            if(Objects.equals(st.getSid(), sid)){
                System.out.println("输入的学号重复，请重新输入！");
                return false;
            }
        }
        return true;
    }

    @Override
    public void findAll() {
        SList=(ArrayList<Student>)StudentIOFile.getSListfromFile();
        if (SList==null||SList.isEmpty()){
            System.out.println("还没有学生存在，请先添加！");
            return;
        }
        System.out.println("姓名\t学号\t性别\t班级\t邮箱\t信息建立时间\t最后修改时间");
        System.out.println("===================================================");
        for(Student st : SList){
            System.out.println(st.getSname()+"\t"+st.getSid()+"\t"+st.getSex()+"\t"+st.getClassname()+"t"+st.getEmail()+"\t"+st.getFirtime()+"\t"+st.getLstime());
        }
        System.out.println("===================================================");
    }

    @Override
    public Student changeBySid(String sid) {
        StudentMenu aa=new StudentMenu();
        SList=(ArrayList<Student>)StudentIOFile.getSListfromFile();
        if (SList==null||SList.isEmpty()){
            System.out.println("还没有学生存在，请先添加！");
            return null;
        }
        for (Student st:SList){
            if(st.getSid()==sid){
                System.out.println("已找到学生，请输入新的学生信息");
                stut=aa.inputStudentInfo();
break;
            }
        }
        return stut;
    }

    @Override
    public boolean checkSexwithSid(Student st) {
        String[] arr=st.getSid().split("\\\\s+");
        if((st.getSex()=="男"&&arr[3]!="1")||(st.getSex()=="女"&&arr[3]!="0")){
            System.out.println("学号的性别与学生的性别不匹配，请重新输入！");
            return false;
        }
        return true;
    }
}
