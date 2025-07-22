package com.bvraju.aidept.StudentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvraju.aidept.StudentApplication.model.Student;

public interface IStudentJPARepository extends JpaRepository<Student, String> {

}
