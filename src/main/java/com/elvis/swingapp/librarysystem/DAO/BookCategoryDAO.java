/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.BookCategory;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;

/**
 *
 * @author elvis
 */
public class BookCategoryDAO extends Connector implements DAO<BookCategory>{

    public BookCategoryDAO() {
        connect();
        try {
            st = con.createStatement();
            final String query = "CREATE TABLE IF NOT EXISTS category"
                    + "("
                    + "categoryID varchar(6) primary key,"
                    + "categoryName varchar(50)"
                    + ");";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
    }
    
    @Override
    public void save(BookCategory object) {
        connect();
        try {
            pst = con.prepareStatement("insert into category values(?,?)");
            pst.setString(1, object.getCategoryId());
            pst.setString(2, object.getCategoryName());
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
            rs = st.executeQuery("select * from category");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}
