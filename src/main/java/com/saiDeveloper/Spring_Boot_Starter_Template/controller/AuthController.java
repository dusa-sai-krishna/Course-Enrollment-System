package com.saiDeveloper.Spring_Boot_Starter_Template.controller;

import com.saiDeveloper.Spring_Boot_Starter_Template.exception.UserException;
import com.saiDeveloper.Spring_Boot_Starter_Template.request.UserLoginRequest;
import com.saiDeveloper.Spring_Boot_Starter_Template.request.UserRegisterRequest;
import com.saiDeveloper.Spring_Boot_Starter_Template.response.AuthResponse;
import com.saiDeveloper.Spring_Boot_Starter_Template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "Authentication", description = "User authentication and registration operations")
public class AuthController {

    @Autowired
    private UserService service;

    @Operation(
        summary = "Register new user",
        description = "Register a new user with the provided credentials and user details"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User successfully registered",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid input - validation error"),
        @ApiResponse(responseCode = "409", description = "User already exists with given email")
    })
    @PostMapping("/register")
    public AuthResponse register(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User registration details", 
            required = true,
            content = @Content(schema = @Schema(implementation = UserRegisterRequest.class))
        )
        @RequestBody @Valid UserRegisterRequest user
    ) throws UserException {
        log.info("Registering user: {}", user);
        return service.saveUser(user);
    }

    @Operation(
        summary = "Authenticate user",
        description = "Login with user credentials to receive JWT token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully authenticated",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))
        ),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "400", description = "Invalid input - validation error")
    })
    @PostMapping("/login")
    public AuthResponse login(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User login credentials", 
            required = true,
            content = @Content(schema = @Schema(implementation = UserLoginRequest.class))
        )
        @RequestBody @Valid UserLoginRequest user
    ) throws UserException {
        log.info("Logging in user: {}", user);
        return service.login(user);
    }
}