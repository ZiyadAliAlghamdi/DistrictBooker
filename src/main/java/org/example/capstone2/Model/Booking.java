package org.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "<booking-model> userId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;


    @NotNull(message = "<booking-model> facilityId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer facilityId;

    @NotNull(message = "<booking-model> start time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME not null")
    private LocalDateTime startTime;


    @NotNull(message = "<booking-model> end time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME not null")
    private LocalDateTime endTime;


    @NotEmpty(message = "<booking-model> status cannot be empty")
    @Pattern(regexp = "^\\b(pending|approved|cancelled|done)\\b$", message = "<booking-model> status value is not valid")
    @Column(columnDefinition = "varchar(40) not null default 'pending' CHECK (status IN ('pending', 'approved', 'cancelled','done'))")
    private String status;


    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;


}
