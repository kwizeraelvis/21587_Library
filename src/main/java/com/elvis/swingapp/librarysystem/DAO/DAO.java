package com.elvis.swingapp.librarysystem.DAO;

import java.sql.ResultSet;

@Deprecated(since = "v3", forRemoval = true)
public interface DAO<T> {
    void save(T object);
    ResultSet display();
}
