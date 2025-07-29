package com.bvraju.aidept.StudentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bvraju.aidept.StudentApplication.model.User;

@RepositoryRestResource(exported = false)
public interface IUserJPARepository extends JpaRepository<User, Integer> {

    public User findByUserid(String userid);

}
