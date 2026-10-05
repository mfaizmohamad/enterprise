package com.__eleven.enterprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "test_taker",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_test_taker_user_schedule",
                columnNames = {"username", "schedule_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, length = 20)
    private String username;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
