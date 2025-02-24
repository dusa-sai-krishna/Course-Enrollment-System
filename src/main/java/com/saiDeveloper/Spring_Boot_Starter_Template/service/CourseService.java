package com.saiDeveloper.Spring_Boot_Starter_Template.service;

import com.saiDeveloper.Spring_Boot_Starter_Template.exception.CourseException;
import com.saiDeveloper.Spring_Boot_Starter_Template.exception.UserException;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.Course;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.CourseSchedule;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.User;
import com.saiDeveloper.Spring_Boot_Starter_Template.repo.CourseRepo;
import com.saiDeveloper.Spring_Boot_Starter_Template.repo.CourseScheduleRepo;
import com.saiDeveloper.Spring_Boot_Starter_Template.repo.UserRepo;
import com.saiDeveloper.Spring_Boot_Starter_Template.request.CourseAdditionRequest;
import com.saiDeveloper.Spring_Boot_Starter_Template.request.CourseScheduleAdditionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepo repo;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseScheduleRepo  scheduleRepo;
    @Autowired
    private UserRepo userRepo;

    public Course addCourse(CourseAdditionRequest request) {
        Course course = new Course();

        //takes care of courseSchedule
        for(CourseScheduleAdditionRequest schedule : request.getCourseSchedules()){
            CourseSchedule  courseSchedule = new CourseSchedule();
            courseSchedule.setDayOfWeek(schedule.getDayOfWeek());
            courseSchedule.setStartTime(schedule.getStartTime());
            courseSchedule.setEndTime(schedule.getEndTime());
            courseSchedule.setCourse(course);
            course.getCourseSchedules().add(courseSchedule);
        }

        course.setName(request.getName());
        course.setEnrollmentLimit(request.getEnrollmentLimit());

        return repo.save(course);
    }

    public Course getCourseById(Long id) throws CourseException {
        Course course = repo.findById(id).orElse(null);
        if(course == null){
            throw new CourseException("Course not found with id:"+id);
        }
        return course;
    }


    public User enroll(Long courseId,  String jwt) throws UserException, CourseException {
        User user = userService.findByJWT(jwt);//primary
        Course course = getCourseById(courseId);//secondary

        //check if user is already enrolled in the course
        if(course.getStudents().contains(user)){
            throw new CourseException("User:"+user.getLastName()+" is already enrolled in the course:"+course.getName());
        }

        //check for enrollment limit.
        if(course.getStudents().size() >= course.getEnrollmentLimit()){
            throw new CourseException("Course:"+course.getName()+" is already full");
        }

        //check if user is already enrolled in another course at the same time or overlapping time.
        List<CourseSchedule> schedules = course.getCourseSchedules();
        for(CourseSchedule schedule:schedules){
            List<Course> courses = scheduleRepo.findOverlappingCourses(schedule.getDayOfWeek(),schedule.getStartTime(),schedule.getEndTime());
            for(Course c:courses){
                if(c.getStudents().contains(user)){
                    throw new CourseException("User:"+user.getLastName()+" is already enrolled in another course:"+c.getName()+" at the same time or overlapping time.");
                }
            }
        }
        course.getStudents().add(user);
        user.getCourses().add(course);
        return userRepo.save(user);
    }

    public User unenroll(Long courseId, String jwt) throws CourseException, UserException {
        User user = userService.findByJWT(jwt);
        Course course =  getCourseById(courseId);

        //if user didn't enrolled in this course
        if(!course.getStudents().contains(user)){
            throw new CourseException("User:"+user.getLastName()+" is not enrolled in the course:"+course.getName());
        }

        user.getCourses().remove(course);
        course.getStudents().remove(user);
        return userRepo.save(user);
    }
}



