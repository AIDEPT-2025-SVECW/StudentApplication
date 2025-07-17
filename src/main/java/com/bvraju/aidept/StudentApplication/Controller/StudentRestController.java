package com.bvraju.aidept.StudentApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvraju.aidept.StudentApplication.Service.IStudentService;
import com.bvraju.aidept.StudentApplication.model.Student;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        System.out.println("In Latest controller");
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudentByRegId(id);
    }

    @PostMapping
    public String createStudent(@RequestBody Student newStudent) {
        int i = studentService.createStudent(newStudent);
        String message = "N/A";
        if (i == 1) {
            message = "New Student bearing regId " + newStudent.getRegId() + " Created Successfully";
        } else {
            message = "Student creation got failed, check the logs";
        }
        return message;
    }

    // PUT – Replace student details completely
    @PutMapping("/{regId}")
    public String updateStudent(@PathVariable String regId, @RequestBody Student updatedStudent) {
        int i = studentService.updateStudent(updatedStudent);
        String message = "N/A";
        if (regId.equals(updatedStudent.getRegId())) {

            if (i == 1) {
                message = "Existing Student bearing regId" + updatedStudent.getRegId() + " is updated Successfully";
            } else {
                message = "Student updation got failed, check the logs";
            }
        } else {
            System.out
                    .println("TODO: throw validation exception as regId in the path variable is not matching the body");
        }
        return message;
    }

    // PATCH – Partially update student details (e.g. name or age)
    // @PatchMapping("/{regId}")
    // public String patchStudent(@PathVariable String regId,Map<String,Object>
    // keyValues){
    // return "Existing Student Partial update Successfully";
    // }

    @DeleteMapping("/{regId}")
    public String deleteStudent(@PathVariable String regId) {
        int i = studentService.deleteStudent(regId);
        String message = "N/A";
        if (i == 1) {
            message = "Existing Student bearing regId" + regId + " is deleted Successfully";
        } else {
            message = "Student deletion got failed, check the logs";
        }
        return message;

    }

}
