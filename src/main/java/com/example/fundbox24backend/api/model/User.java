package com.example.fundbox24backend.api.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;    // TODO: Random generation of username
    @NotNull
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotNull
    @NotBlank(message = "Password cannot be empty")     // TODO: Password validation
    private String password;

    @OneToOne @Nullable
    private Location homeLocation = null;

    private double homeRadius = 1.0;

    private boolean useCurrentLocation = true;

    private boolean receiveNotifications = true;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<LostReport> lostReports = new ArrayList<LostReport>();

    @OneToMany(mappedBy = "finder", cascade = CascadeType.ALL)
    private List<FoundReport> foundReports = new ArrayList<FoundReport>();

    @OneToMany(mappedBy = "reportVisitor", cascade = CascadeType.ALL)
    private List<Chat> initiatedChats = new ArrayList<Chat>();

    @OneToMany(mappedBy = "reportCreator", cascade = CascadeType.ALL)
    private List<Chat> ownChats = new ArrayList<Chat>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages = new ArrayList<Message>();



    public User(String name, String email, String password)
    {
        this.name = (name != null) ? name : "RANDOM NAME";  // TODO: Random generation of username
        this.email = email;
        this.password = password;

    }

    public User()
    {

    }
}
