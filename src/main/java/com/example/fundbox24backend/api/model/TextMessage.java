package com.example.fundbox24backend.api.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class TextMessage extends Message
{
    @NotNull
    @NotBlank(message = "Message must not be empty.")
    @Size(min=1, max=255, message="message must have between {min} and {max} characters.")
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