package com.bvraju.aidept.StudentApplication.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    /*
     * IMPORTANT: Transactional Behavior of TRUNCATE vs DELETE
     *
     * - TRUNCATE TABLE is a Data Definition Language (DDL) command. In most
     * relational databases,
     * including MySQL and PostgreSQL, DDL statements such as TRUNCATE, ALTER, and
     * DROP
     * trigger an automatic commit before and after their execution.
     * - As a result, the effects of a TRUNCATE cannot be rolled back, even if this
     * method is annotated with @Transactional.
     * This means that the table will be emptied irreversibly, regardless of any
     * subsequent errors or rollbacks in the transaction.
     *
     * - DELETE FROM is a Data Manipulation Language (DML) command, and remains
     * fully transactional.
     * When using DELETE within a @Transactional method, all changes can be rolled
     * back if the transaction fails or is explicitly rolled back.
     *
     * For this reason, we use DELETE here instead of TRUNCATE to ensure that the
     * table
     * only gets cleared if the surrounding transaction successfully completes.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cleanup() {
        // String query = "Truncate table student.student_details";
        // jdbcTemplate.execute(query);
        String query = "delete from student.student_details";
        jdbcTemplate.update(query);
    }

}
