/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

import java.sql.ResultSet;

/**
 *
 * @author elvis
 */
public interface DAO<T> {
    void save(T object);
    ResultSet display();
}
