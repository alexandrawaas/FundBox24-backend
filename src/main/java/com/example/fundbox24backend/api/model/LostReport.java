package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LostReport extends Report
{
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastSeenDate;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL) private Location lastSeenLocation;
    @OneToOne(cascade = CascadeType.ALL) private Location lostLocation = lastSeenLocation;
    private double lostRadius;
    @NotNull
    @ManyToOne private User owner;

    public LostReport(String title, String description, String imagePath, boolean isFinished, Category category, LocalDateTime lastSeenDate, Location lastSeenLocation, Location lostLocation, double lostRadius, User owner)
    {
        super(title, description, imagePath, isFinished, category);
        this.lastSeenDate = lastSeenDate;
        this.lastSeenLocation = lastSeenLocation;
        this.lostLocation = (lostLocation != null ? lostLocation : lastSeenLocation);
        this.lostRadius = lostRadius;
        this.owner = owner;
    }

    public LostReport()
    {

    }

    @Override
    public User getCreator()
    {
        return this.owner;
    }
}