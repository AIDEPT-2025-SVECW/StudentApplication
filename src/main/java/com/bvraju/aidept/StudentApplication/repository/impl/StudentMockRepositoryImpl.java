package com.bvraju.aidept.StudentApplication.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bvraju.aidept.StudentApplication.model.Student;
import com.bvraju.aidept.StudentApplication.repository.IStudentRepository;

import jakarta.annotation.PostConstruct;

//Commented to ignore the implementation
// @Repository
public class StudentMockRepositoryImpl implements IStudentRepository {

    private List<Student> students;

    @Value("${college.name}")
    private String collegeName;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student currStudent = new Student("Student" + i, "RegId-" + i, "AIML");
            currStudent.setCollege(collegeName);
            students.add(currStudent);
        }
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findByRegId(String regId) {
        return new Student("Mocked Student", regId, "Mocked Dept");
    }

    @Override
    public int deleteByRegId(String regId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int saveOrUpdate(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
