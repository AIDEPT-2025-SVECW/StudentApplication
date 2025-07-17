package com.bvraju.aidept.StudentApplication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class StudentApplication {

	   @Value("${mysql.database.url}")
    private String url;

    @Value("${mysql.database.username}")
    private String userName;

    @Value("${mysql.database.password}")
    private String password;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	public DataSource getDataSource(){
		DataSource ds = new DriverManagerDataSource(url,userName,password);
		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(){
		JdbcTemplate jdt = new JdbcTemplate(getDataSource());
		return jdt;
	}

}
