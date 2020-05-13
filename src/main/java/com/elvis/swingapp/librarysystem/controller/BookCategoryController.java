package com.elvis.swingapp.librarysystem.controller;

import com.elvis.swingapp.librarysystem.model.BookCategory;
import com.elvis.swingapp.librarysystem.utils.FactoryIntializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookCategoryController {
    public void saveCategory(BookCategory category){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    public BookCategory findCategoryById(Long categoryId){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        BookCategory category = null;
        try{
            transaction = session.beginTransaction();
            category = session.get(BookCategory.class, categoryId);
            transaction.commit();
        }catch(HibernateError e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return category;
    }
    public BookCategory findCategoryByName(String categoryName){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        BookCategory category = null;
        try{
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BookCategory> criteriaQuery = criteriaBuilder
                    .createQuery(BookCategory.class);
            Root<BookCategory> root = criteriaQuery.from(BookCategory.class);
            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get("categoryName"), categoryName));
            category = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
        }catch(HibernateError ex){
            if(transaction != null)transaction.rollback();
            ex.printStackTrace();
        }finally{
            session.close();
        }
        return category;
    }
    
    public List<BookCategory> listAllCategories(){
        Session session = FactoryIntializer.intializSessionFactory().openSession();
        Transaction transaction = null;
        List<BookCategory> categorys = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            categorys = session.createQuery("from BookCategory").list();
            transaction.commit();
        } catch (HibernateError e) {
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return categorys;
    }
}
