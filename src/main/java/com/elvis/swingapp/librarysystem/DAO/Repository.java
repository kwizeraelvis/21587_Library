package com.elvis.swingapp.librarysystem.DAO;

public interface Repository<T, E> {
   T findCategoryByName(T categoryName);
   E findCategoryById(T categoryId);
}
