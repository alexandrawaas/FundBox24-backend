package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class FoundReport extends Report
{
    private Date foundDate;
    @OneToOne private Location foundLocation;
    @OneToOne private Location currentLocation;
    @ManyToOne private User finder;

    public FoundReport(String title, String description, String imagePath, Date createdAt, boolean isFinished, Category category, List<Chat> chats, Date foundDate, Location foundLocation, Location currentLocation, User finder)
    {
        super(title, description, imagePath, createdAt, isFinished, category, chats);
        this.foundDate = foundDate;
        this.foundLocation = foundLocation;
        this.currentLocation = currentLocation;
        this.finder = finder;
    }

    public FoundReport()
    {

    }
}
