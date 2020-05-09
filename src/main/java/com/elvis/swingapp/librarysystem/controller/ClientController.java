package com.elvis.swingapp.librarysystem.controller;

import com.elvis.swingapp.librarysystem.model.Client;
import com.elvis.swingapp.librarysystem.utils.FactoryIntializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientController {
    public void saveClient(Client client){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }catch(HibernateError e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    public Client findClient(String SearchParameter, Object paramater){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        Client client = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = builder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root).where(builder.equal(root.get(SearchParameter), paramater));
            client = session.createQuery(query).getSingleResult();
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return client;
    }
    public void updateclient(Long id, Client UpdateData){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client toBeUpdated = (Client) session.get(Client.class, id);
            toBeUpdated.setFirstName(UpdateData.getFirstName());
            toBeUpdated.setLastName(UpdateData.getLastName());
            toBeUpdated.setCategory(UpdateData.getCategory());
            toBeUpdated.setPhoneNumber(UpdateData.getPhoneNumber());
            toBeUpdated.setEmail(UpdateData.getEmail());
            session.update(toBeUpdated);
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    public void deleteClient(Long clientId){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Client toBeDeleted = (Client) session.get(Client.class, clientId);
            session.delete(toBeDeleted);
            transaction.commit();
        }catch(HibernateError e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    public List<Client> listAllClients(){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        List<Client> clients = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            clients = session.createQuery("from Client").list();
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return  clients;
    }
    
}
