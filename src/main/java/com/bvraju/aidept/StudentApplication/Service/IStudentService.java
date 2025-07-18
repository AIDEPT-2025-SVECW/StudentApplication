package com.bvraju.aidept.StudentApplication.Service;

import java.io.IOException;
import java.util.List;

import com.bvraju.aidept.StudentApplication.model.Student;

public interface IStudentService {

    public List<Student> getStudents();

    public Student getStudentByRegId(String regId);

    public int createStudent(Student newStudent);

    public int updateStudent(Student updatedStudent);

    public int deleteStudent(String regId);

    public boolean loadStudents() throws IOException;

}
