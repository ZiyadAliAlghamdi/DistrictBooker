package org.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "<feedback-model> user id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "<feedback-model> facility id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer facilityId;

    @NotEmpty(message = "<feedback-model> message cannot be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String message;

    @NotEmpty(message = "<feedback-model> status cannot be null")
    @Pattern(regexp = "^(NEW|IN_PROGRESS|CLOSED)$")
    private String status = "NEW";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}
