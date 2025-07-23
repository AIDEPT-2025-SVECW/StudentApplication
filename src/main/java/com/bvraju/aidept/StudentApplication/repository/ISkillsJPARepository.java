package com.bvraju.aidept.StudentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bvraju.aidept.StudentApplication.model.Skills;

@RepositoryRestResource(path = "skills")
public interface ISkillsJPARepository extends JpaRepository<Skills, Integer> {

}
