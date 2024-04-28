package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime createdAt;
    private boolean isFinished;
    @ManyToOne private Category category;
    @OneToMany(fetch = FetchType.EAGER) private List<Chat> chats = new ArrayList<Chat>();

    public Report(String title, String description, String imagePath, LocalDateTime createdAt, boolean isFinished, Category category)
    {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.isFinished = isFinished;
        this.category = category;
    }

    public Report()
    {

    }

    public void addChat(Chat chat)
    {
        this.chats.add(chat);
    }
}