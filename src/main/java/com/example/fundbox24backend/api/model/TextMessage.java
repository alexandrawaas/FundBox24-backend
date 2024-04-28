package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class TextMessage extends Message
{
    private String text;

    public TextMessage(LocalDateTime sentAt, User sender, String text)
    {
        super(sentAt, sender);
        this.text = text;
    }

    public TextMessage()
    {
    }
}