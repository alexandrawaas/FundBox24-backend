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
    @OneToOne private Location lastSeenLocation;
    @OneToOne private Location lostLocation = lastSeenLocation;
    private double lostRadius;
    @NotNull
    @ManyToOne private User owner;

    public LostReport(String title, String description, String imagePath, boolean isFinished, Category category, LocalDateTime lastSeenDate, Location lastSeenLocation, Location lostLocation, double lostRadius, User owner)
    {
        super(title, description, imagePath, isFinished, category);
        this.lastSeenDate = lastSeenDate;
        this.lastSeenLocation = lastSeenLocation;
        if(lostLocation != null) this.lostLocation = lostLocation;      // TODO: passt das so?
        this.lostRadius = lostRadius;
        this.owner = owner;
    }

    public LostReport()
    {

    }
}