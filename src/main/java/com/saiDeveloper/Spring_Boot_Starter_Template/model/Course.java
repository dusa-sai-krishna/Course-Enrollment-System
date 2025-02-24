package com.saiDeveloper.Spring_Boot_Starter_Template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="course")
@Scope("prototype")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int enrollmentLimit;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CourseSchedule> courseSchedules = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    @ToString.Exclude
    private List<User> students = new ArrayList<>();

}
