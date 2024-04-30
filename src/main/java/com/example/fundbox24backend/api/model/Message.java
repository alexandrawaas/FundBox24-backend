package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime sentAt;
    @ManyToOne private User sender;

    public Message(LocalDateTime sentAt, User sender)
    {
        this.sentAt = sentAt;
        this.sender = sender;
    }

    public Message()
    {

    }
}