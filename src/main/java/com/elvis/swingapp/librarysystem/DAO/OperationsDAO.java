/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.CheckIn;
import com.elvis.swingapp.librarysystem.model.CheckOut;
import com.elvis.swingapp.librarysystem.model.Status;
import com.elvis.swingapp.librarysystem.utils.BookCheckInUnsupportedAction;
import com.elvis.swingapp.librarysystem.utils.BookCheckoutUnsupportedAction;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elvis
 */
public class OperationsDAO extends Connector implements UserRepository<CheckIn, CheckOut>{

    public OperationsDAO() {
        connect();
        try {
            st = con.createStatement();
            final String query = "CREATE TABLE IF NOT EXISTS operations"
                    + "("
                    + "clientID varchar(50),"
                    + "bookid varchar(6),"
                    + "dateTime date,"
                    + "status varchar(20),"
                    + "FOREIGN KEY (clientID) REFERENCES client(regno) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (bookid) REFERENCES book(bookId) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ");";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
    }
    
    @Override
    public void CheckIn(CheckIn object) {
        connect();
        if(object.getStatus() != Status.CHECK_OUT.toString()){
            try {
                throw new BookCheckInUnsupportedAction("The book is already checked in");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                pst = con.prepareStatement("update operation set status = ? where clientID = ?;");
                pst.setString(1, object.getStatus());
                pst.setString(2, object.getClient().getRegno());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                disConnect();
            }
        }
    }

    @Override
    public void Checkout(CheckOut object) {
        connect();
        if(object.getStatus() != Status.CHECK_IN.toString()){
            try {
                throw new BookCheckoutUnsupportedAction("The book has alrady been taken;");
            } catch (BookCheckoutUnsupportedAction ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                pst = con.prepareStatement("insrert into operation values(?,?,?,?)");
                pst.setString(1, object.getClient().getRegno());
                pst.setString(2, object.getBook().getBookId());
                pst.setDate(3, Date.valueOf(object.getDateTime()));
                pst.setString(4, object.getStatus());
                pst.executeUpdate();                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                disConnect();
            }
        }
    }

    @Override
    public ResultSet display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
