package com.elvis.swingapp.librarysystem.DAO;

import java.sql.ResultSet;

public interface UserRepository<T,E> {
    void CheckIn(T object);
    void Checkout(E object);
    ResultSet display();
}
