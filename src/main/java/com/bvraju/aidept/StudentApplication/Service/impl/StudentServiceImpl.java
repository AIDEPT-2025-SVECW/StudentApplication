package com.bvraju.aidept.StudentApplication.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvraju.aidept.StudentApplication.Service.IStudentService;
import com.bvraju.aidept.StudentApplication.model.Student;
import com.bvraju.aidept.StudentApplication.repository.IStudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByRegId(String regId) {
        Student studentDetails = null;
        if (Student.isValidRegId(regId)) {
            studentDetails = studentRepository.findByRegId(regId);
        } else {
            System.out.println("TODO: throw Validation exception and handle using        RestControllerAdvice");
        }
        return studentDetails;
    }

    public int createStudent(Student newStudent) {
        int noOfStudentsCreated = 0;
        if (newStudent.isValid()) {
            noOfStudentsCreated = studentRepository.saveOrUpdate(newStudent);
        } else {
            System.out.println("TODO: need to throw validation exception and handle        using");

        }
        return noOfStudentsCreated;
    }

    public int updateStudent(Student updatedStudent) {
        int noOfStudentsUpdated = 0;
        if (updatedStudent.isValid()) {
            noOfStudentsUpdated = studentRepository.saveOrUpdate(updatedStudent);
        } else {
            System.out
                    .println("TODO: need to throw validation exception and handle using        RestController Advice");
        }
        return noOfStudentsUpdated;
    }

    public int deleteStudent(String regId) {
        int noOfStudentsDeleted = 0;
        if (Student.isValidRegId(regId)) {
            Student existingStudentDtls = getStudentByRegId(regId);
            if (existingStudentDtls != null) {
                noOfStudentsDeleted = studentRepository.deleteByRegId(regId);
            } else {
                System.out.println(
                        "TODO: need to handle resource doesnt exists to update exception and display            constructive message");
            }
        } else {
            System.out.println("TODO: need to handle validation exception using        RestControllerAdvice");
        }
        return noOfStudentsDeleted;
    }

}
