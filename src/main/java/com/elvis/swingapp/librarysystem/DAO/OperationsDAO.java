/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.CheckIn;
import com.elvis.swingapp.librarysystem.model.CheckOut;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Checkout(CheckOut object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
