package com.elvis.swingapp.librarysystem.DAO;

import java.sql.ResultSet;

public interface DAO<T> {
    void save(T object);
    ResultSet display();
}
