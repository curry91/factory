package cn.curry.test;
import cn.curry.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Curry on 2017/1/12.
 */
public class MyTest {
    Session  session;
    Transaction transaction;
    @Before
    public  void before(){
        Configuration cfg=new Configuration().configure();
        SessionFactory factory=cfg.buildSessionFactory();
        session = factory.getCurrentSession();
        transaction= session.beginTransaction();
    }

    @Test
    public void add(){
        Student student=new Student();
        //student.setId(1);
        student.setName("哇哇哇");
        student.setAge(20);
        session.save(student);

    }
    @Test
    public void update(){
        Student stu=(Student)session.load(Student.class,new Integer(2));
        stu.setName("嘿嘿");
        //session.saveOrUpdate(stu);
        session.merge(stu);

    }
   @Test
    public void delete(){
        Student stu=(Student)session.load(Student.class,new Integer(21));
        session.delete(stu);

    }
    @Test
    public void select(){
        Student stu=(Student)session.get(Student.class,new Integer(2));
        //Student stu2=(Student)session.get(Student.class,new Integer(2));
        System.out.println(stu);
       // System.out.println(stu2);
        System.out.println(stu.getName());
    }
    @After
    public void after(){
        transaction.commit();
        //System.out.println(stu.getName());
        //session.close();
    }


}
