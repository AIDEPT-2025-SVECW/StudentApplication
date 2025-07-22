package com.bvraju.aidept.StudentApplication.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_details")
public class Student {
    @NonNull
    private String name;
    @NonNull
    @Id
    @Column(name = "regid")
    private String regId;
    @NonNull
    private String dept;
    @Column(name = "section")
    private String sec;

    private String college;

    @JsonIgnore
    public boolean isValid() {
        boolean status = false;

        // Check if any required field is empty
        if (!StringUtils.isEmpty(this.getName()) &&
                !StringUtils.isEmpty(this.getRegId()) &&
                !StringUtils.isEmpty(this.getDept())) {

            // Name validation: should contain only letters and spaces
            boolean isNameValid = this.getName().matches("^[a-zA-Z\\s]+$");

            // RegId validation: length between 9 to 12

            boolean isRegIdValid = Student.isValidRegId(this.getRegId());

            // Dept validation: must be one of allowed constants
            List<String> allowedDepts = Arrays.asList("EEE", "ECE", "IT", "CSE", "AIML", "AIDS");
            boolean isDeptValid = allowedDepts.contains(this.getDept());

            // All validations passed
            if (isNameValid && isRegIdValid && isDeptValid) {
                status = true;
            }
        }

        return status;
    }

    public static boolean isValidRegId(String regId) {
        boolean status = false;
        if (!StringUtils.isEmpty(regId)) {
            int regIdLength = regId.length();
            status = regIdLength >= 9 && regIdLength <= 12;
        }
        return status;
    }

}
