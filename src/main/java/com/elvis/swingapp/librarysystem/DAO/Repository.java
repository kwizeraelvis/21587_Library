package com.elvis.swingapp.librarysystem.DAO;

@Deprecated(since = "v3", forRemoval = true)
public interface Repository<T, E> {
   T findCategoryByName(T categoryName);
   E findCategoryById(T categoryId);
}
