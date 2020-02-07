/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.Client;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;


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
        connect();
        try{
            pst = con.prepareStatement("insert into client values(?,?,?,?,?,?)");
            pst.setString(1, object.getRegno());
            pst.setString(2, object.getFirstName());
            pst.setString(3, object.getLastName());
            pst.setString(4, object.getPhoneNumber());
            pst.setString(5, object.getEmail());
            pst.setString(6, object.getCategory());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disConnect();
        }
    }

    @Override
    public ResultSet display() {
        connect();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from client;");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rs;
    }
}
