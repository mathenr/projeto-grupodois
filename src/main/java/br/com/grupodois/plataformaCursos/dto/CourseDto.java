package br.com.grupodois.plataformaCursos.dto;

import br.com.grupodois.plataformaCursos.model.Course;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDto {
    private Long id;
    private String name;
    private String category;
    private double workload;
    private String status;
    private boolean has_certificate;
    private double evaluation;
    private LocalDateTime createdAt;

    public CourseDto(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.category = course.getCategory();
        this.workload = course.getWorkload();
        this.status = course.getStatus();
        this.has_certificate = course.isHas_certificate();
        this.evaluation = course.getEvaluation();
        this.createdAt = course.getCreatedAt();
    }

    public static Page<CourseDto> convert(Page<Course> courses){
        return courses.map(CourseDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getWorkload() {
        return workload;
    }

    public String getStatus() {
        return status;
    }

    public boolean isHas_certificate() {
        return has_certificate;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}