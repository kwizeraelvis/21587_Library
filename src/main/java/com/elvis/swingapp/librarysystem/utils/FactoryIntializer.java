package com.elvis.swingapp.librarysystem.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryIntializer {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory intializSessionFactory(){
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
}
