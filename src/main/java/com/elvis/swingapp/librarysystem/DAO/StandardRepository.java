/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvis.swingapp.librarysystem.DAO;

/**
 *
 * @author elvis
 */
public interface StandardRepository<Class, ID> {
    void update(Class object);
    void delete(ID objectID);
}
