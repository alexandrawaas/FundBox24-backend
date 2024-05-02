package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private LocalDateTime sentAt;
    @NotNull
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