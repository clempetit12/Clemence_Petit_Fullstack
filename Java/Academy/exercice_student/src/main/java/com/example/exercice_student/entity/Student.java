package com.example.exercice_student.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {


    private UUID id;

    @NotBlank
    @NotNull(message = "prénom non nul")
    @Size(min=3, message = "3 minimum svp")
    private String firstName;

    @NotBlank
    @NotNull(message = "nom non nul")
    @Size(min=3, message = "3 minimum svp")
    private String lastName;

    @NotNull
   @Min(18)
    @Max(60)
    private Integer age;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;



}
