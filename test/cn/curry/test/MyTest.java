package cn.curry.test;

import cn.curry.entity.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Curry on 2017/1/10.
 */
public class MyTest {
    Session session;
    Transaction transaction;
    @Before
    public  void before(){
        Configuration cfg=new Configuration().configure();
        SessionFactory factory=cfg.buildSessionFactory();
        session = factory.getCurrentSession();
        transaction= session.beginTransaction();
    }
    @After
    public void after(){
        transaction.commit();
    }
    @Test
    public void add(){
        String dateString = "2012-12-06 ";
        Date date=null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Factory factory=new Factory();
        factory.setType("CDMA-1");
        factory.setPrice(650);
        factory.setDatetime(date);
        session.save(factory);
    }
    @Test
    public void updatefactory(){
        String dateString = "2017-12-12 ";
        Date date=null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Factory factory=session.load(Factory.class,new Integer(24));
        factory.setPrice(800);
        factory.setDatetime(date);
        session.merge(factory);
    }
    @Test
    public void deleteFactory(){
        Factory factory=session.load(Factory.class,new Integer(24));
        session.delete(factory);
    }
}
