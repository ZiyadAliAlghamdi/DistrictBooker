package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "<admin-model> username cannot empty")
    @Size(min = 6, max = 40, message = "<admin-model> username length cannot be less than 6 or more than 40")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String username;

    @NotEmpty(message = "<admin-model> email cannot be empty")
    @Email(message = "<admin-model> mail must be valid")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "<admin-model> phone number cannot be null")
    @Pattern(
            regexp = "^\\+966\\d{9}$",
            message = "<admin-model> phone number must be in the format +966XXXXXXXXX"
    )
    @Column(columnDefinition = "varchar(20) not null")
    private String phone;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column
    private Instant updatedAt;
}