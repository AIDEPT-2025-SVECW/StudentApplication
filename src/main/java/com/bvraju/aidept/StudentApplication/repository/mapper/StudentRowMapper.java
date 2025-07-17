package com.bvraju.aidept.StudentApplication.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bvraju.aidept.StudentApplication.model.Student;

public class StudentRowMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
       String name = rs.getString("name");
            String regId = rs.getString("regId");
            String dept = rs.getString("dept");
            String section = rs.getString("section");
            String college = rs.getString("college");
            Student currStudent = new Student(name,regId,dept);
            currStudent.setCollege(college);
            currStudent.setSec(section);
            return currStudent;
    }

}
