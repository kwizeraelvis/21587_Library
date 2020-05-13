package com.elvis.swingapp.librarysystem.controller;

import com.elvis.swingapp.librarysystem.model.AuthUser;
import com.elvis.swingapp.librarysystem.utils.FactoryIntializer;
import com.elvis.swingapp.librarysystem.utils.PasswordUtils;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AuthenticationController {
    private static Session session;
    private static String SALT = "Ave6kXOQ1F";
    
    public void createNewUser(AuthUser user){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String userpassword = user.getPassword();
            user.setPassword(PasswordUtils
                    .generateSecurePassword(userpassword, SALT));
            session.save(user);
            transaction.commit();
            
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }  
    public Boolean loginUser(String username, String password){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        Boolean canLogin = true;
        AuthUser user = null;
        try{
            transaction = session.beginTransaction();
            final String query = "From AuthUser u where u.username = :username";
            Query q = session.createQuery(query);
            q.setParameter("username", username);
            user = (AuthUser) q.getSingleResult();
            if(PasswordUtils.verifyUserPassword(password,user.getPassword(), SALT)){
                canLogin = true;
            }
            transaction.commit();
        }catch(HibernateError e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return canLogin;
    }
}
