package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.CheckIn;
import com.elvis.swingapp.librarysystem.model.CheckOut;
import com.elvis.swingapp.librarysystem.model.Status;
import com.elvis.swingapp.librarysystem.utils.BookCheckInUnsupportedAction;
import com.elvis.swingapp.librarysystem.utils.BookCheckoutUnsupportedAction;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
@Deprecated
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
        if(object.getBook().getStatus() == Status.CHECK_IN.toString()){
            try {
                throw new BookCheckInUnsupportedAction("The book is already checked in");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{
            try {
                pst = con.prepareStatement("update operation set status = ? where clientID = ? and bookid = ?;");
                pst.setString(1, object.getStatus());
                pst.setString(2, object.getClient().getRegno());
                pst.setString(3, object.getBook().getBookId());
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
        if(!object.getStatus().equals(Status.CHECK_OUT.toString()) && object.getStatus().equals(Status.CHECK_IN.toString())){
            object.getBook().setStatus(Status.CHECK_OUT.toString());
            object.setStatus(object.getBook().getStatus());
            try {
                pst = con.prepareStatement("insert into operations values(?,?,?,?)");
                pst.setString(1, object.getClient().getRegno());
                pst.setString(2, object.getBook().getBookId());
                pst.setDate(3, Date.valueOf(object.getDateTime()));
                pst.setString(4, object.getBook().getStatus());
                pst.executeUpdate();                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                disConnect();
            }
        }else{
            try {
                throw new BookCheckoutUnsupportedAction("The book is already taken");
            } catch (BookCheckoutUnsupportedAction ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @Override
    public ResultSet display() {
        connect();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from operations;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet displayOpertaionCategory(String category){
        connect();
        try{
            pst = con.prepareStatement("select * from operations where status = ?");
            pst.setString(1, category);
            rs = pst.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
       return rs;
    }

}
