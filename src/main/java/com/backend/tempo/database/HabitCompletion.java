package com.backend.tempo.database;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "habit_completions")
public class HabitCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private boolean completed;
    
    @Column
    private String notes;
}