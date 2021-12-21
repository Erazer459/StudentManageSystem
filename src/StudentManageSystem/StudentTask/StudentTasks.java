package StudentManageSystem.StudentTask;
import StudentManageSystem.Student;

import java.util.*;
public interface StudentTasks {
    public void save(Student st);//此方法用来添加学生对象st进入List中
    public void update(Student st);//此方法用来更新修改后的学生对象
    public boolean findBySid(String sid);//通过学号来查询学生
    public void delBySid(String sid);//通过学号来删除学生
    public void findByYear(String year);//查询入学年份
    public void findBySex(String sex);//查询通过性别查找
    public void findByMajor(String major);//查询专业
    public void findByClass(String classname);//查询班级
    public void findBySname(String name);//通过名字查询
    public boolean check(String sid);//查重，不能存在学号相同的学生
    public void findAll();//展示学生列表
    public Student changeBySid(String sid);//通过学号修改学生信息
    public boolean checkSexwithSid(Student st);// 检查学号的性别与学生的性别是否匹配
}
