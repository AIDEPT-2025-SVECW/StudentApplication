package com.bvraju.aidept.StudentApplication.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bvraju.aidept.StudentApplication.model.Student;
import com.bvraju.aidept.StudentApplication.repository.IStudentRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Repository
public class StudentJDBCRepositoryImpl implements IStudentRepository{

    private Connection connection;

    @Value("${mysql.database.url}")
    private String url;

    @Value("${mysql.database.username}")
    private String userName;

    @Value("${mysql.database.password}")
    private String password;

    @PostConstruct
    private void init(){
        try {
            // Step 1: Load and register the JDBC driver
            // This step is handled by Class.forName("com.mysql.cj.jdbc.Driver") in older versions,
            // but in modern JDBC, this step is optional if you use a JDBC 4.0+ compliant driver.
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: Establish the connection
            // Using DriverManager to connect to the database by providing URL, username, and password.
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException  e) {
            System.out.println("Unable to establish connection, please check connectin properties");
            e.printStackTrace();
        }
    }



    @Override
    public List<Student> findAll(){
        List<Student> students = new ArrayList<>();
        String query = "select * from student.student_details";
        Statement st = null;
        ResultSet rs = null;
       try{
        // Step 3: Create the statement
        // Creating a PreparedStatement to perform parameterized SQL operations (safe from SQL injection).
        st = connection.createStatement();
        // Step 4: Set parameters and execute the query
        // Setting values into the placeholders and executing the SQL statement.
        rs = st.executeQuery(query);
        // Step 5: (Optional in INSERT) Process the result
        // For INSERT/UPDATE/DELETE, we usually check the affected rows.
        // For SELECT, we would use ResultSet and iterate over it.
        while(rs.next()){
            String name = rs.getString("name");
            String regId = rs.getString("regId");
            String dept = rs.getString("dept");
            String section = rs.getString("section");
            String college = rs.getString("college");
            Student currStudent = new Student(name,regId,dept);
            currStudent.setCollege(college);
            currStudent.setSec(section);
            students.add(currStudent);
        }
       }catch(SQLException ex){
        System.out.println("Unable to execute query");
        ex.printStackTrace();
       }finally{
        
        try {
            // Step 6: Close the resources
            // It's important to release database resources to avoid memory leaks.
            rs.close();
            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }
        return students;
    }

    @Override
    public Student findByRegId(String regId){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=null;
        try{
        ps = connection.prepareStatement("select * from student.student_details where regId = ?");
        ps.setString(1, regId);
        rs = ps.executeQuery();
        if(rs.next()){
            String name = rs.getString("name");
            String regIdop = rs.getString("regId");
            String dept = rs.getString("dept");
            String section = rs.getString("section");
            String college = rs.getString("college");
            student = new Student(name, regIdop, dept);
            student.setCollege(college);
            student.setSec(section);
        }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return student;
    }

    @PreDestroy
    private void destroy(){
        try {
            // Step 6: Close the resources
            // It's important to release database resources to avoid memory leaks.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Unable to CLOSE connection, please check connectin properties");
            e.printStackTrace();
        }
    }



 



    @Override
    public int deleteByRegId(String regId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

      @Override
    public int saveOrUpdate(Student student){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
