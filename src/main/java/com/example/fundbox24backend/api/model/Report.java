package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String title;
    private String description;
    private String imagePath;
    private Date createdAt;
    private boolean isFinished;
    @ManyToOne private Category category;
    @OneToMany private List<Chat> chats;

}