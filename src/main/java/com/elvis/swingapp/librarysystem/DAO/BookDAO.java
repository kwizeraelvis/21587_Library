/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.Book;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;

/**
 *
 * @author elvis
 */
public class BookDAO extends Connector implements DAO<Book>{

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
                    + "pages int"
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
            pst = con.prepareStatement("insert into book values(?,?,?,?,?,?);");
            pst.setString(1, object.getBookId());
            pst.setString(2, object.getTitle());
            pst.setString(3, object.getPublishingHouse());
            pst.setDate(4, object.getDateofPublication());
            pst.setString(5, object.getAuthor());
            pst.setInt(6, object.getPages());
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
    
}
