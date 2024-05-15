package com.example.fundbox24backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Chat
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @OneToMany(cascade = CascadeType.ALL) private List<Message> messages = new ArrayList<Message>();
    @NotNull
    @ManyToOne private Report report;
    @NotNull
    @ManyToOne private User reportVisitor;
    @NotNull
    @ManyToOne private User reportCreator;

    public Chat(Report report, User reportVisitor, User reportCreator)
    {
        this.report = report;
        this.reportVisitor = reportVisitor;
        this.reportCreator = reportCreator;
    }

    public Chat()
    {
    }

    public void addMessage(Message message)
    {
        this.messages.add(message);
    }
}
