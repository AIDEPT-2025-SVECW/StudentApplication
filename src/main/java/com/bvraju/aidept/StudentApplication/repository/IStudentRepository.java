package com.bvraju.aidept.StudentApplication.repository;

import java.util.List;

import com.bvraju.aidept.StudentApplication.model.Student;

public interface IStudentRepository {

    public List<Student> findAll();

    public Student findByRegId(String regId);

    public int saveOrUpdate(Student student);

    public int deleteByRegId(String regId);

    public default void cleanup() {
        System.out.println("Default implementation of clean up");
    }

}
