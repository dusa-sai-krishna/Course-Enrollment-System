package com.saiDeveloper.Spring_Boot_Starter_Template.repo;

import com.saiDeveloper.Spring_Boot_Starter_Template.model.Course;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CourseScheduleRepo  extends JpaRepository<CourseSchedule,Long> {


// This query finds overlapping courses by checking:
// 1. If the courses are on the same day of week
// 2. If either:
//    - The new start time falls within an existing course's time slot OR
//    - The new end time falls within an existing course's time slot
// However, this query misses the case where a new course completely encompasses an existing course
// A more complete query would be:
@Query("SELECT cs.course FROM CourseSchedule cs WHERE cs.dayOfWeek = :dayOfWeek AND " +
        "((cs.startTime >= :startTime AND cs.endTime <= :endTime)" + // New course encompasses an existing course
       "OR " +
        "(cs.startTime <= :startTime AND cs.endTime >= :startTime) OR " + //new course starts in between existing course
       "(cs.startTime <= :endTime AND cs.endTime >= :endTime) )") // new course ends in between existing course

    List<Course> findOverlappingCourses(@Param("dayOfWeek") String dayOfWeek,
                                        @Param("startTime")LocalTime startTime,
                                        @Param("endTime")LocalTime endTime);

}
