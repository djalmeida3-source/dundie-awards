package com.ninjaone.dundie_awards.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "occured_at")
    private LocalDateTime occuredAt;

    @Column(name = "event")
    private String event;

    public Activity() {

    }

    public Activity(LocalDateTime localDateTime, String event) {
        super();
        this.occuredAt = localDateTime;
        this.event = event;
    }

    public LocalDateTime getOccuredAt() {
        return occuredAt;
    }

    public String getEvent() {
        return event;
    }

}
