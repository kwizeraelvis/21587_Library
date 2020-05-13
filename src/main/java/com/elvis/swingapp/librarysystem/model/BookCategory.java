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
    private Long categoryId;
    @Column(name = "categoryName")
    private String categoryName;

    public BookCategory() {
    }

    public BookCategory(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
