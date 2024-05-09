package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ImageMessage extends Message
{
    @NotNull
    @NotBlank(message = "Image path must not be empty.")
    private String imagePath;

    public ImageMessage(LocalDateTime sentAt, User sender, String imagePath)
    {
        super(sentAt, sender);
        this.imagePath = imagePath;
    }

    public ImageMessage()
    {

    }

    @Override
    public String getContent()
    {
        return this.imagePath;
    }
}