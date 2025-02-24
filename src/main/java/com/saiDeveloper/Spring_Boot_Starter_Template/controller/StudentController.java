package com.saiDeveloper.Spring_Boot_Starter_Template.controller;

import com.saiDeveloper.Spring_Boot_Starter_Template.exception.CourseException;
import com.saiDeveloper.Spring_Boot_Starter_Template.exception.UserException;
import com.saiDeveloper.Spring_Boot_Starter_Template.model.User;
import com.saiDeveloper.Spring_Boot_Starter_Template.service.CourseService;
import com.saiDeveloper.Spring_Boot_Starter_Template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Enrollment Management", description = "APIs for managing student course enrollments")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Operation(
        summary = "Health check endpoint",
        description = "Simple endpoint to verify student access"
    )
    @ApiResponse(responseCode = "200", description = "Student access verified")
    @GetMapping("/")
    private String hello() {
        return "Hello Student";
    }

    @Operation(
        summary = "Enroll in course",
        description = "Enroll authenticated student in a specific course"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully enrolled in course",
            content = @Content(schema = @Schema(implementation = User.class))
        ),
        @ApiResponse(responseCode = "404", description = "Course not found"),
        @ApiResponse(responseCode = "400", description = "Already enrolled or other error"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required")
    })
    @PostMapping("/enroll/id/{courseId}")
    private ResponseEntity<User> enroll(
        @Parameter(description = "ID of the course to enroll in", required = true)
        @PathVariable("courseId") Long courseId,
        @Parameter(hidden = true)
        @RequestHeader("Authorization") String jwt
    ) throws CourseException, UserException {
        User user = courseService.enroll(courseId, jwt);
        return new ResponseEntity<>(user, org.springframework.http.HttpStatus.OK);
    }

    @Operation(
        summary = "Get user details",
        description = "Retrieve user profile and enrollment information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User found",
            content = @Content(schema = @Schema(implementation = User.class))
        ),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required")
    })
    @GetMapping("/id/{userId}")
    private ResponseEntity<User> getUserById(
        @Parameter(description = "ID of the user to retrieve", required = true)
        @PathVariable("userId") Long userId
    ) throws UserException {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, org.springframework.http.HttpStatus.OK);
    }

    @Operation(
        summary = "Unenroll from course",
        description = "Remove authenticated student from a specific course"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully unenrolled from course",
            content = @Content(schema = @Schema(implementation = User.class))
        ),
        @ApiResponse(responseCode = "404", description = "Course not found"),
        @ApiResponse(responseCode = "400", description = "Not enrolled or other error"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required")
    })
    @DeleteMapping("/enroll/id/{courseId}")
    private ResponseEntity<User> unenroll(
        @Parameter(description = "ID of the course to unenroll from", required = true)
        @PathVariable("courseId") Long courseId,
        @Parameter(hidden = true)
        @RequestHeader("Authorization") String jwt
    ) throws CourseException, UserException {
        User user = courseService.unenroll(courseId, jwt);
        return new ResponseEntity<>(user, org.springframework.http.HttpStatus.OK);
    }
}