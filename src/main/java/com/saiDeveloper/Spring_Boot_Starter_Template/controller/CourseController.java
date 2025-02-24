package com.saiDeveloper.Spring_Boot_Starter_Template.controller;

import com.saiDeveloper.Spring_Boot_Starter_Template.exception.CourseException;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.Course;
import com.saiDeveloper.Spring_Boot_Starter_Template.request.CourseAdditionRequest;
import com.saiDeveloper.Spring_Boot_Starter_Template.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/admin/course")
@Tag(name = "Course Management", description = "APIs for managing course information (Admin only)")
@SecurityRequirement(name = "bearerAuth")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Operation(
        summary = "Health check endpoint",
        description = "Simple endpoint to verify admin access"
    )
    @ApiResponse(responseCode = "200", description = "Admin access verified")
    @GetMapping("/")
    private String hello() {
        return "Hello Admin";
    }

    @Operation(
        summary = "Add new course",
        description = "Create a new course with the provided details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Course created successfully",
            content = @Content(schema = @Schema(implementation = Course.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid input - validation error"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    @PostMapping("/add")
    private ResponseEntity<Course> addCourse(
        @Parameter(description = "Course details", required = true)
        @Valid @RequestBody CourseAdditionRequest request
    ) {
        Course course = courseService.addCourse(request);
        return new ResponseEntity<>(course, org.springframework.http.HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get course by ID",
        description = "Retrieve detailed information about a specific course"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Course found",
            content = @Content(schema = @Schema(implementation = Course.class))
        ),
        @ApiResponse(responseCode = "404", description = "Course not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    @GetMapping("/id/{courseId}")
    private ResponseEntity<Course> getCourse(
        @Parameter(description = "ID of the course to retrieve", required = true)
        @PathVariable("courseId") Long courseId
    ) throws CourseException {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, org.springframework.http.HttpStatus.OK);
    }
}