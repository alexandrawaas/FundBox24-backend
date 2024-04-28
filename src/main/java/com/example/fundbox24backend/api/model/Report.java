package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String imagePath;
    private Date createdAt;
    private boolean isFinished;
    @ManyToOne private Category category;
    @OneToMany private List<Chat> chats;

    public Report(String title, String description, String imagePath, Date createdAt, boolean isFinished, Category category, List<Chat> chats)
    {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.isFinished = isFinished;
        this.category = category;
        this.chats = chats;
    }

    public Report()
    {

    }
}