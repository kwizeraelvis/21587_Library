package com.elvis.swingapp.librarysystem.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkIn")
public class CheckIn{
    @OneToOne
    @Column(name = "client")
    @JoinColumn(name = "regno")
    private Client client;
    @OneToOne
    @Column(name = "book")
    @JoinColumn(name = "bookId")
    private Book book;
    @Column(name = "date")
    private LocalDate dateTime;
    @Column(name = "status")
    private String status;
    @Id
    private Long id;

    public CheckIn() {
    }

    public CheckIn(Client client, Book book, LocalDate dateTime, String status, Long id) {
        this.client = client;
        this.book = book;
        this.dateTime = dateTime;
        this.status = status;
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.client);
        hash = 37 * hash + Objects.hashCode(this.book);
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
        final CheckIn other = (CheckIn) obj;
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
