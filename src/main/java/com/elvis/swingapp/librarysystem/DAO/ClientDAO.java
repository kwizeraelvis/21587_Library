package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.Client;
import com.elvis.swingapp.librarysystem.utils.Connector;
import com.elvis.swingapp.librarysystem.utils.GeneralUtility;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends Connector implements DAO<Client>,Repository<String, Client>,StandardRepository<Client, String> {

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

    @Override
    public String findCategoryByName(String categoryName) {
       connect();
       String[] names = GeneralUtility.StringSplit(categoryName);
       String clientId = "";
        try {
            pst = con.prepareStatement("select regno from client where firstname = ? and lastname = ?");
            pst.setString(1, names[0]);
            pst.setString(2, names[1]);
            rs = pst.executeQuery();
            if(rs.next()){
                clientId = rs.getString("regno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return clientId;
    }

    @Override
    public Client findCategoryById(String categoryId) {
        connect();
        Client client = new Client();
        try {
            pst = con.prepareStatement("select * from client where regno = ?");
            pst.setString(1, categoryId);
            rs = pst.executeQuery();
            if(rs.next()){
                client.setRegno(categoryId);
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setEmail(rs.getString("email"));
                client.setPhoneNumber(rs.getString("phonenumber"));
                client.setCategory(rs.getString("category"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return client;
    }

    @Override
    public void update(Client object) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(String objectID) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
