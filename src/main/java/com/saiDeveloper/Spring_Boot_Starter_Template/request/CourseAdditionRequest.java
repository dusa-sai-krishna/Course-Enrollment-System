package com.saiDeveloper.Spring_Boot_Starter_Template.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAdditionRequest {
    @NotBlank(message = "Course name cannot be blank")
    private String name;

    @NotEmpty(message = "Course schedules cannot be empty")
    private List<CourseScheduleAdditionRequest> courseSchedules = new ArrayList<>();

    @Min(value = 1, message = "Limit must be at least 1")
    private int enrollmentLimit;

}
