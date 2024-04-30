package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ImageMessage extends Message
{
    private String imagePath;

    public ImageMessage(LocalDateTime sentAt, User sender, String imagePath)
    {
        super(sentAt, sender);
        this.imagePath = imagePath;
    }

    public ImageMessage()
    {

    }
}