package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank(message = "Title of report cannot be empty")
    private String title;
    private String description;
    private String imagePath;   // TODO: implement default image per category
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean isFinished = false;
    @NotNull
    @ManyToOne private Category category;
    @OneToMany(cascade = CascadeType.ALL) private List<Chat> chats = new ArrayList<Chat>();

    public Report(String title, String description, String imagePath, boolean isFinished, Category category)
    {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
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

    public abstract User getCreator();
}