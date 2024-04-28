package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LostReport extends Report
{
    private Date lastSeenDate;
    @OneToOne private Location lastSeenLocation;
    @OneToOne private Location lostLocation;
    private double lostRadius;
    @ManyToOne private User owner;
}