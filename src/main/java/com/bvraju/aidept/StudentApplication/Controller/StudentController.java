package com.bvraju.aidept.StudentApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bvraju.aidept.StudentApplication.Service.IStudentService;
import com.bvraju.aidept.StudentApplication.model.Student;

@Controller
@Deprecated
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("/studentsLegacy")
    @ResponseBody
    public String getStudents(Model model) {
        System.out.println("In Controller");
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        System.out.println("size of students is " + students.size());
        return "studentDisplay";
    }

    @GetMapping("/studentsLegacy1")
    @ResponseBody
    public List<Student> getStudents() {
        System.out.println("In Latest controller");
        return studentService.getStudents();
    }

   

}
