package com.elvis.swingapp.librarysystem.DAO;

import com.elvis.swingapp.librarysystem.model.BookCategory;
import com.elvis.swingapp.librarysystem.utils.Connector;
import java.sql.ResultSet;

@Deprecated(since = "v3", forRemoval = true)
public class BookCategoryDAO extends Connector implements DAO<BookCategory>,Repository<String, BookCategory>{

    @Deprecated(since = "v3", forRemoval = true)
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
            pst.setString(1, "");
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

    @Override
    public String findCategoryByName(String categoryName) {
        connect();
        String categoryId = "";
        try {
            pst = con.prepareStatement("select categoryID from category where categoryName = ?");
            pst.setString(1, categoryName);
            rs = pst.executeQuery();
            if(rs.next()){
                categoryId = rs.getString("categoryID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return categoryId;
    }

    @Override
    public BookCategory findCategoryById(String categoryId) {
        connect();
        BookCategory category = new BookCategory();
        try {
            pst = con.prepareStatement("select * from category where categoryID = ?");
            pst.setString(1, categoryId);
            rs = pst.executeQuery();
            if(rs.next()){
                category.setCategoryId(1l);
                category.setCategoryName(rs.getString("categoryName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            disConnect();
        }
        return category;
    }
    
}
