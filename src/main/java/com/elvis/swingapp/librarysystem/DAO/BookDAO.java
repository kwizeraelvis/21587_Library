/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.Book;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author elvis
 */
public class BookDAO extends Connector implements DAO<Book>,Repository<String, Book>,StandardRepository<Book, String>{
    BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();

    public BookDAO() {
        connect();
        try {
            st = con.createStatement();
            final String query = "CREATE TABLE IF NOT EXISTS book"
                    + "("
                    + "bookId varchar(5) primary key,"
                    + "title varchar(50),"
                    + "publishingHouse varchar(50),"
                    + "dateofPublication date,"
                    + "author varchar(50),"
                    + "pages int,"
                    + "categoryId varchar(6),"
                    + "FOREIGN KEY (categoryId) REFERENCES category(categoryID) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ");";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
    }
    
    @Override
    public void save(Book object) {
         connect();
         try {
            pst = con.prepareStatement("insert into book values(?,?,?,?,?,?,?);");
            pst.setString(1, object.getBookId());
            pst.setString(2, object.getTitle());
            pst.setString(3, object.getPublishingHouse());
            pst.setDate(4, object.getDateofPublication());
            pst.setString(5, object.getAuthor());
            pst.setInt(6, object.getPages());
            pst.setString(7, object.getCategory().getCategoryId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
             disConnect();
         }
    }

    @Override
    public ResultSet display() {
        connect();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from book;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public String findCategoryByName(String categoryName) {
        connect();
        String bookId = "";
        try {
            pst = con.prepareStatement("select bookId from book where title = ?;");
            pst.setString(1, categoryName);
            rs = pst.executeQuery();
            if(rs.next()){
                bookId = rs.getString("bookId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return bookId;
    }

    @Override
    public Book findCategoryById(String categoryId) {
        connect();
        Book book = new Book();
        try {
            pst = con.prepareStatement("select * from book where bookId = ?");
            pst.setString(1, categoryId);
            rs = pst.executeQuery();
            if(rs.next()){
                book.setBookId(categoryId);
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublishingHouse(rs.getString("publishingHouse"));
                book.setDateofPublication(rs.getDate("dateofPublication"));
                book.setPages(rs.getInt("pages"));
                book.setCategory(bookCategoryDAO.findCategoryById(rs.getString("categoryId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return book;
    }

    public void saveBatch(Set<Book> books){
        connect();
        try {
            pst = con.prepareStatement("insert into book values(?,?,?,?,?,?,?);");
            for(Book book : books){
                pst.setString(1, book.getBookId());
                pst.setString(2, book.getTitle());
                pst.setString(3, book.getPublishingHouse());
                pst.setDate(4, book.getDateofPublication());
                pst.setString(5, book.getAuthor());
                pst.setInt(6, book.getPages());
                pst.setString(7, book.getCategory().getCategoryId());
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
    }
    
    @Override
    public void update(Book object) {
        //ToDo implement DAO standard Repository methods
    }

    @Override
    public void delete(String objectID) {
        //ToDo implement DAO standard Repository Methods.
    }
    
}
