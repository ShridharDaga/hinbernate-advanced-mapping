package com.example.hibernate.entity;

//import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    @NotBlank
    private int id;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Column(name = "email")
    @NotBlank
    private String email;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
