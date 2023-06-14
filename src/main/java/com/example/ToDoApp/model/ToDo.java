package com.example.ToDoApp.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table (name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;

    @Column
    @NonNull
    private String title;

    @Column
    @NonNull
    private Date date;

    @Column
    @NonNull
    private String status;

    public ToDo() {
    }

    public ToDo(long id, String title, Date date, String status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
