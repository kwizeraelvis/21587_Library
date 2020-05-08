package com.elvis.swingapp.librarysystem.model;

import com.elvis.swingapp.librarysystem.utils.UpdatedClassFunctionality;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@UpdatedClassFunctionality(since = "v3")
public class BookCategory {
    @Id
    @Column(name = "categoryId")
    private String categoryId;
    @Column(name = "categoryName")
    private String CategoryName;

    public BookCategory() {
    }

    public BookCategory(String categoryId, String CategoryName) {
        this.categoryId = categoryId;
        this.CategoryName = CategoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.categoryId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookCategory other = (BookCategory) obj;
        if (!Objects.equals(this.categoryId, other.categoryId)) {
            return false;
        }
        return true;
    }
    
    
}
