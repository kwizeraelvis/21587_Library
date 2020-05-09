package com.elvis.swingapp.librarysystem.DAO;

@Deprecated(since = "v3", forRemoval = true)
public interface StandardRepository<Class, ID> {
    void update(Class object);
    void delete(ID objectID);
}
