package StudentManageSystem;
import StudentManageSystem.StudentAddAllinformations.AddAllinformations;
import StudentManageSystem.StudentAddAllinformations.AddAllinformationsImp;
import StudentManageSystem.StudentTask.StudentTasksImp;

import java.io.*;
import java.util.*;
public class StudentMenu {
    private Scanner input=new Scanner(System.in);
    private StudentTasksImp ImpS=new StudentTasksImp();
    private AddAllinformationsImp ImpA=new AddAllinformationsImp();
    public Student inputStudentInfo(){
        Student st=new Student();
        System.out.println("输入学生的姓名：");
        st.setSname(ImpA.inputSname());
        System.out.println("请输入学生的学号：");
        st.setSid(ImpA.inputSid());
        System.out.println("请输入学生的性别：");
        st.setSex(ImpA.inputSex());
        System.out.println("请输入学生的班级：");
        st.setClassname(ImpA.inputClassname());
        System.out.println("请输入学生的邮箱：");
        st.setEmail(ImpA.inputEmail());
        return st;
    }
    //创建菜单
    public void menu(){
        Student st=null;
        String sid=null;
        boolean flag=false;
 try {
     while (true){
            System.out.println("===================================================");
            System.out.println("SELECT:");
            System.out.println("[1]添加用户\t");
            System.out.println("[2]修改用户\t");
            System.out.println("[3]删除用户\t");
            System.out.println("[4]查询所有用户\t");
            System.out.println("[5]查询指定用户\t");
            System.out.println("[6]退出");
            System.out.println("===================================================");
            System.out.println("INPUT:");
            int op=input.nextInt();
            switch (op){
                case 1:
                    System.out.println("执行添加用户");
                    System.out.println("请输入学生信息：");
                    st=inputStudentInfo();
                    if(!ImpS.check(sid)){//检查学号是否输入重复
                        break;
                    }
                    if (!ImpS.checkSexwithSid(st)){//检查性别与学号的性别是否匹配
                        break;
                    }
                    ImpS.save(st);
                    break;
                case 2:
                    System.out.println("执行修改用户");
                    System.out.println("请输入要修改的学生的学号：");
                    sid=ImpA.inputSid();
                    if (!ImpS.findBySid(sid)){
                        System.out.println("该学生不存在！");
                    }else{
                        System.out.println("请输入要修改的学生信息:");
                        st=ImpS.changeBySid(sid);
                        ImpS.update(st);
                    }
                    break;
                case 3:
                    System.out.println("执行删除用户");
                    System.out.println("请输入要删除的学生的学号：");
                    sid=ImpA.inputSid();
                    ImpS.delBySid(sid);
                    break;
                case 4:
                    System.out.println("执行查询用户");
                    System.out.println("请选择你的查询模式");
                    System.out.println("===================================================");
                    System.out.println("SELECT:");
                    System.out.println("[1]通过学号查询\t");
                    System.out.println("[2]通过专业查询\t");
                    System.out.println("[3]通过入学年份查询\t");
                    System.out.println("[4]通过性别查询\t");
                    System.out.println("[5]通过班级名称查询\t");
                    System.out.println("[6]通过名字查询\t");
                    System.out.println("===================================================");
                    System.out.println("INPUT");
                    int slc=input.nextInt();
                    switch (slc){
                        case 1:
                            System.out.println("请输入你要查询的学生的学号：");
                            String id=ImpA.inputSid();
                            ImpS.findBySid(id);
                            break;
                        case 2:
                            System.out.println("请输入你要查询的学生的专业：");
                            String major=ImpA.inputChinese();
                            ImpS.findByMajor(major);
                            break;
                        case 3:
                            System.out.println("请输入你要查询的学生的入学年份：");
                            String year=ImpA.inputYear();
                            ImpS.findByYear(year);
                            break;
                        case 4:
                            System.out.println("请输入你要查询的学生的性别：");
                            String sex=ImpA.inputSex();
                            ImpS.findBySex(sex);
                            break;
                        case 5:
                            System.out.println("请输入你要查询到学生的班级名称：");
                            String classname=ImpA.inputClassname();
                            ImpS.findByClass(classname);
                            break;
                        case 6:
                            System.out.println("请输入你要查询的学生的名字：");
                            String name=ImpA.inputSname();
                            ImpS.findBySname(name);
                            break;
                        default:
                            System.out.println("请输入1-6的数字！");
                            break;
                    }
                case 5:
                    System.out.println("显示学生列表");
                    ImpS.findAll();
                    break;
                case 0:
                    System.out.println("谢谢使用，系统退出！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("请选择从0-5之间的数字！");
                    break;
            }
     }
 } catch (Exception e) {
     System.out.println("未知错误！");
     e.printStackTrace();
 }
    }
}
