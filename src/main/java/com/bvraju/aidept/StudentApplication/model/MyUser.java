package com.bvraju.aidept.StudentApplication.model;

import com.bvraju.aidept.StudentApplication.configuration.ROLE;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class MyUser {
    @Id
    private int id;
    @NonNull
    private String userid;
    @NonNull
    private String password;
    // Persist the enum as a String in the database (recommended for readability and
    // safety)
    @Enumerated(EnumType.STRING)
    private ROLE role;

}
