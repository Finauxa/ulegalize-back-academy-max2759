package com.ulegalize.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "gender")
    @Getter
    @Setter
    private String gender;

    @Column(name = "comment")
    @Getter
    @Setter
    private String comment;

    @Column(name = "date")
    @Getter
    @Setter
    private LocalDateTime date;

}
