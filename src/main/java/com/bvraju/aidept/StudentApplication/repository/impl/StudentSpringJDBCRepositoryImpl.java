package com.bvraju.aidept.StudentApplication.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bvraju.aidept.StudentApplication.model.Student;
import com.bvraju.aidept.StudentApplication.repository.IStudentRepository;
import com.bvraju.aidept.StudentApplication.repository.mapper.StudentRowMapper;

@Repository
@Primary
public class StudentSpringJDBCRepositoryImpl implements IStudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findAll() {
        System.out.println("in spring jdbc repository");
        String query = "select regId,name,dept,college,section as sec from student.student_details";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<Student>(Student.class));
    }

    @Override
    public Student findByRegId(String regId) {
        String query = "select regId,name,dept,college,section as sec from    student.student_details where regId=?";
        Student existingStudent = null;
        List<Student> matchingStudents = jdbcTemplate.query(query, new BeanPropertyRowMapper<Student>(Student.class),
                regId);
        if (matchingStudents != null && matchingStudents.size() == 1) {
            existingStudent = matchingStudents.get(0);
        } else {
            System.out.println("TODO: handle resource not found exception using    advicer");
        }
        return existingStudent;
    }

    @Override
    public int deleteByRegId(String regId) {
        String query = "delete from student.student_details where regId=?";
        Object[] args = new Object[] { regId };
        int noOfRecordsUpdated = jdbcTemplate.update(query, args);
        return noOfRecordsUpdated;
    }

    @Override
    public int saveOrUpdate(Student student) {
        int noOfRecordsImpacted = 0;
        String regId = student.getRegId();
        Student existingStudentIfAny = findByRegId(regId);
        if (existingStudentIfAny != null) {
            noOfRecordsImpacted = update(student);
        } else {
            noOfRecordsImpacted = save(student);
        }
        return noOfRecordsImpacted;
    }

    public int save(Student newStudent) {
        String query = "insert into student.student_details (regId,name,dept,college,section) values (?,?,?,?,?)";
        Object[] args = new Object[] { newStudent.getRegId(), newStudent.getName(), newStudent.getDept(),
                newStudent.getCollege(), newStudent.getSec() };
        int noOfRecordsInserted = jdbcTemplate.update(query, args);
        return noOfRecordsInserted;
    }

    public int update(Student updateStudent) {
        String query = "update student.student_details set name=?,dept=?,college=?,section=? where regId=?";
        Object[] args = new Object[] { updateStudent.getName(), updateStudent.getDept(), updateStudent.getCollege(),
                updateStudent.getSec(), updateStudent.getRegId(), };
        int noOfRecordsUpdated = jdbcTemplate.update(query, args);
        return noOfRecordsUpdated;
    }

}
