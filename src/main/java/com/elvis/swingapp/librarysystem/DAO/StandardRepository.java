package com.elvis.swingapp.librarysystem.DAO;

public interface StandardRepository<Class, ID> {
    void update(Class object);
    void delete(ID objectID);
}
