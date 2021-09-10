package br.com.grupodois.plataformaCursos.dto;

import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.model.StatusCourse;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String category;
    private double workload;
    private StatusCourse status;
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
}