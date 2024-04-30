package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LostReport extends Report
{
    private LocalDateTime lastSeenDate;
    @OneToOne private Location lastSeenLocation;
    @OneToOne private Location lostLocation;
    private double lostRadius;
    @ManyToOne private User owner;

    public LostReport(String title, String description, String imagePath, LocalDateTime createdAt, boolean isFinished, Category category, LocalDateTime lastSeenDate, Location lastSeenLocation, Location lostLocation, double lostRadius, User owner)
    {
        super(title, description, imagePath, createdAt, isFinished, category);
        this.lastSeenDate = lastSeenDate;
        this.lastSeenLocation = lastSeenLocation;
        this.lostLocation = lostLocation;
        this.lostRadius = lostRadius;
        this.owner = owner;
    }

    public LostReport()
    {

    }
}