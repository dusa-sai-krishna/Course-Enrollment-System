package com.saiDeveloper.Spring_Boot_Starter_Template.repo;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
}
