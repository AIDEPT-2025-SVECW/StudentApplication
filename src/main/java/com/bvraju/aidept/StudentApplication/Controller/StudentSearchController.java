package com.bvraju.aidept.StudentApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvraju.aidept.StudentApplication.Service.IStudentService;
import com.bvraju.aidept.StudentApplication.model.Student;

@RestController
@RequestMapping("/students/search")
public class StudentSearchController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public Student getStudentByParam(@RequestParam(name = "name") String name,
            @RequestParam(name = "dept") String dept) {
        return new Student(name, "id", dept);
    }

}
