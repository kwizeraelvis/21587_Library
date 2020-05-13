package com.elvis.swingapp.librarysystem.controller;

import com.elvis.swingapp.librarysystem.model.CheckIn;
import com.elvis.swingapp.librarysystem.model.CheckOut;
import com.elvis.swingapp.librarysystem.utils.FactoryIntializer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OperationsController {
    private static Session session;
    public void CheckIn(CheckIn checkIn){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
                transaction = session.beginTransaction();
                session.save(checkIn);
                transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    public void CheckOut(CheckOut checkOut){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
                transaction = session.beginTransaction();
                session.save(checkOut);
                transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    
    public List<CheckIn> listAllCheckIns(){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        List<CheckIn> checkIns = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            checkIns = session.createQuery("from CheckIn").list();
            transaction.commit();
        }catch(HibernateError e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return checkIns;
    }
    public List<CheckOut> listAlCheckOuts(){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        List<CheckOut> checkOuts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            checkOuts = session.createQuery("from CheckOut").list();
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return checkOuts;
    }
       
}
