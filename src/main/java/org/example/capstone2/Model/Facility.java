package org.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Facility {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "<facility-model> adminId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer adminId;

    @NotEmpty(message = "<facility-model> name cannot be empty")
    @Size(min = 6,max = 40, message = "<facility-model> name length must be between 6 and 40")
    @Column(columnDefinition = "varchar(40) not null")
    private String name;


    @NotEmpty(message = "<facility-model> description cannot be empty")
    @Size(max = 255, message = "<facility-model> description length cannot be more than 255 chars")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;


    @NotNull(message = "<facility-model> categoryId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotNull(message = "<facility-model> neighborhood ID cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer neighborhoodId;


    @NotNull(message = "<facility-model> capacity cannot be null")
    @Positive(message = "<facility-model> capacity cannot be negative or zero")
    @Column(columnDefinition = "int not null check (capacity > 0)")
    private Integer capacity;


}
