package StudentManageSystem.StudentAddAllinformations;

import StudentManageSystem.MajorNum;
import StudentManageSystem.StudentTask.StudentTasksImp;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class AddAllinformationsImp implements AddAllinformations{
private Scanner input=new Scanner(System.in);
    private static ConcurrentHashMap<String, String> nums;
    @Override
    public  String inputSname() {
        String name;//使用正则表达式判断输入是否为空
        String regex = "/^[\\s\\S]*.*[^\\s][\\s\\S]*$/";
        name= input.nextLine();
        boolean flag=name.matches(regex);
        if(flag){
            System.out.println("名字输入不能为空，请重新输入！");
            inputSname();
            return null;
        }
            return name;
    }

    @Override
    public String inputSid() {//输入学号，判断专业码，性别是否正确，以及学号格式是否正确
StudentTasksImp aa=new StudentTasksImp();
        String sid = input.nextLine();
        String[] arr = sid.split(" ");//使用split函数将将学号分割成几个部分
        int len = arr.length;
        if (len < 4) {
            System.out.println("学号格式不正确，请重新输入！");
            inputSid();
            return null;
        }
        if (!MajorNum.checkNumKey(arr[1])) {
            System.out.println("不存在该专业码，请重新输入学号！");
            inputSid();
            return null;
        }
        if (!Objects.equals(arr[3], "1") && !Objects.equals(arr[3], "0")) {
            System.out.println("性别格式错误，请重新输入！");
            inputSid();
            return null;
        }
        return sid;
    }
    @Override
    public String inputSex() {

        String sex=input.nextLine();
        String regex = "/^[\\s\\S]*.*[^\\s][\\s\\S]*$/";//判断非空
        boolean flag=sex.matches(regex);
        if (flag){
        System.out.println("性别输入不能为空，请重新输入！");
        inputSex();
        }
         if(!sex.equals("男") && !sex.equals("女")){
            System.out.println("性别输入非法，请重新输入！");
            inputSex();
            return null;
        }
            return sex;
    }

    @Override
    public String inputClassname() {
    String classname=input.nextLine();
        String regex = "/^[\\s\\S]*.*[^\\s][\\s\\S]*$/";
        boolean flag=classname.matches(regex);
        if(flag){
            System.out.println("班级输入不能为空，请重新输入！");
            inputClassname();
            return null;
        }
            return classname;
    }

    @Override
    public String inputEmail() {
        String email=input.nextLine();
        String regex1="/^[\\s\\S]*.*[^\\s][\\s\\S]*$/";
        String regex2="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//邮箱正则表达式
        boolean flag1=email.matches(regex1);
        boolean flag2=email.matches(regex2);
        if (flag1){
            System.out.println("邮箱输入不能为空，请重新输入！");
            inputEmail();
            return null;
        }
         if(!flag2){
            System.out.println("邮箱格式错误，请重新输入！");
            inputEmail();
            return null;
        }
            return email;
    }

    @Override
    public String inputChinese() {//检查输入中文的合法性
        String major=input.nextLine();
        String regex="\\u4e00-\\u9fa5";
        boolean flag=major.matches(regex);
        if(!flag) {
            System.out.println("请输入正确的专业名称！");
            inputChinese();
            return null;
        }
        return major;
    }

    @Override
    public String inputYear() {
    Integer year=input.nextInt();
    String years=String.valueOf(year);
    String regex="^[1-9]\\d*$";//匹配正整数的正则表达式
        boolean flag=years.matches(regex);
        if (!flag){
            System.out.println("输入的年份非法，请重新输入！");
            inputYear();
            return null;
        }
        return years;
    }
}
