package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "<rating-model> userid cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "<rating-model> facilityId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer facilityId;

    @NotNull(message = "<rating-model> rating value cannot be null")
    @Max(value = 5,message = "<rating-model> rating value cannot be more than 5")
    @Min(value = 1, message = "<rating-model> rating value cannot be less than 1")
    @Column(columnDefinition = "int not null")
    private Integer ratingValue;

    @NotEmpty(message = "<rating-model> comment cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String comment;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}
