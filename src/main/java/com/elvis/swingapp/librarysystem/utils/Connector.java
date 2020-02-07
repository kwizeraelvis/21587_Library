/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author elvis
 */
public class Connector {
    
    private String DbUrl = "jdbc:postgresql://localhost:5432/library";
    private String DbUsername = "postgres";
    private String DBPassword = "root";
    
    protected Connection con;
    protected Statement st;
    protected PreparedStatement pst;
    protected ResultSet rs;
    
    
    public void connect(){
        try{
            con = DriverManager.getConnection(DbUrl, DbUsername, DBPassword);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void disConnect(){
        try{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
            if(pst != null){
                pst.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
