package com.elvis.swingapp.librarysystem.model;

import com.elvis.swingapp.librarysystem.utils.UpdatedClassFunctionality;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@UpdatedClassFunctionality(since = "v3")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Long bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "publishingHouse")
    private String publishingHouse;
    @Column(name = "dateofPublication")
    private Date dateofPublication;
    @Column(name = "author")
    private String author;
    @Column(name = "pages")
    private int pages;
    @OneToOne(targetEntity = BookCategory.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookCategory category;
    @Column(name = "status")
    private String status = Status.CHECK_IN.toString();

    public Book() {
    }

    public Book(Long bookId, String title, String publishingHouse, Date dateofPublication, String author, int pages, BookCategory category) {
        this.bookId = bookId;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.dateofPublication = dateofPublication;
        this.author = author;
        this.pages = pages;
        this.category = category;
    }
    
    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Date getDateofPublication() {
        return dateofPublication;
    }

    public void setDateofPublication(Date dateofPublication) {
        this.dateofPublication = dateofPublication;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.bookId);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.bookId, other.bookId)) {
            return false;
        }
        return true;
    }
    
    
}
