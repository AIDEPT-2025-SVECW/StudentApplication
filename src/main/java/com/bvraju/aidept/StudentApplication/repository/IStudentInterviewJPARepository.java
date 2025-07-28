package com.bvraju.aidept.StudentApplication.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.bvraju.aidept.StudentApplication.model.StudentInterviews;

@RepositoryRestResource(path = "interviews", collectionResourceRel = "interviews")
public interface IStudentInterviewJPARepository extends MongoRepository<StudentInterviews, String> {

    public List<StudentInterviews> findByRegId(@Param("regId") String regId);

    public List<StudentInterviews> findByDateOfInterviewAfterAndFinalResultIsNull(
            @Param("dateOfInterview") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date interviewDate);

}
