package com.elvis.swingapp.librarysystem.controller;

import com.elvis.swingapp.librarysystem.model.Book;
import com.elvis.swingapp.librarysystem.utils.FactoryIntializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookController {
    private static Session session;
    public void saveBook(Book book){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    
    public List<Book> listAllBooks(){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        List<Book> books = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            books = session.createQuery("from Book").list();
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return books;
    }
    public Book findBookByTitle(String bookTitle){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        Book book = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root)
                    .where(builder.equal(root.get("title"), bookTitle));
            book = session.createQuery(criteriaQuery).uniqueResult();
            transaction.commit();            
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return book;
    }
    public Book findBookById(Long bookId){
        session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction  = null;
        Book book = null;
        try {
            transaction = session.beginTransaction();
            book = session.get(Book.class, bookId);
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return book;
    }
    
}
