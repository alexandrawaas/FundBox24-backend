package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class FoundReport extends Report
{
    private Date foundDate;
    @OneToOne private Location foundLocation;
    @OneToOne private Location currentLocation;
    @ManyToOne private User finder;
}
