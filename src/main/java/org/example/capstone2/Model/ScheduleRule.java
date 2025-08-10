package org.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScheduleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "<scheduleRule-model> facilityId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer facilityId;


    @NotEmpty(message = "<scheduleRule-model> days of week cannot be empty")
    @Column(columnDefinition = "SET('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY')", nullable = false)
    private String daysOfWeek;


    @NotNull(message = "<scheduleRule-model> open time cannot be null")
    @Column(nullable = false)
    private LocalTime openTime;


    @NotNull(message = "<scheduleRule-model> close time cannot be null")
    @Column(nullable = false)
    private LocalTime closeTime;
}
