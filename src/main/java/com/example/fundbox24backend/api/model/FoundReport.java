package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class FoundReport extends Report
{
    private LocalDateTime foundDate;
    @OneToOne private Location foundLocation;
    @OneToOne private Location currentLocation;
    @ManyToOne private User finder;

    public FoundReport(String title, String description, String imagePath, LocalDateTime createdAt, boolean isFinished, Category category, List<Chat> chats, LocalDateTime foundDate, Location foundLocation, Location currentLocation, User finder)
    {
        super(title, description, imagePath, createdAt, isFinished, category);
        this.foundDate = foundDate;
        this.foundLocation = foundLocation;
        this.currentLocation = currentLocation;
        this.finder = finder;
    }

    public FoundReport()
    {

    }
}
