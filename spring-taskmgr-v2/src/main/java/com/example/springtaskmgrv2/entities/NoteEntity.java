package com.example.springtaskmgrv2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity {

    @Column(name = "body", nullable = false, length = 500)
    String body;

    @ManyToOne
    TaskEntity task;
}
