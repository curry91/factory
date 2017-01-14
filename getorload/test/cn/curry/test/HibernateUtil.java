package cn.curry.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Curry on 2017/1/12.
 */
public class HibernateUtil {
    private static final ThreadLocal sessionTl=new ThreadLocal();
    private static Configuration cfg;
    private final static SessionFactory sf;
    static {
        try{
            cfg=new Configuration().configure();
            sf=cfg.buildSessionFactory();
        }catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session currentSession(){
        Session session=(Session)sessionTl.get();
        if(session==null){
            session=sf.openSession();
            sessionTl.set(session);
        }
        return session;
    }
    @SuppressWarnings("unchecked")
    public static void closeSession(){
        Session session=(Session)sessionTl.get();
        sessionTl.set(null);
        session.close();
    }
}
