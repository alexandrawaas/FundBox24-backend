package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Chat
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @OneToMany private List<Message> messages;
    @ManyToOne private Report report;
    @ManyToOne private User reportVisitor;
    @ManyToOne private User reportCreator;
}
