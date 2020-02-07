/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.Client;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elvis
 */
public class ClientDAO extends Connector implements DAO<Client> {

    public ClientDAO() {
        connect();
        try{
            st = con.createStatement();
            final String query = "CREATE TABLE IF NOT EXISTS client"
                    + "("
                    + "regno varchar(50) primary key,"
                    + "firstname varchar(50),"
                    + "lastname varchar(50),"
                    + "phonenumber varchar(13),"
                    + "email varchar(30),"
                    + "category varchar(20)"
                    + ");";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disConnect();
        }
    }
    
    
    @Override
    public void save(Client object) {
        
    }

    @Override
    public void display() {
        
    }
    
}
