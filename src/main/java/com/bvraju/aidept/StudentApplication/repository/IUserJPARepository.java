package com.bvraju.aidept.StudentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bvraju.aidept.StudentApplication.model.MyUser;

@RepositoryRestResource(exported = false)
public interface IUserJPARepository extends JpaRepository<MyUser, Integer> {

    public MyUser findByUserid(String userid);

}
