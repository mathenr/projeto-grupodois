package br.com.grupodois.plataformaCursos.dto.form.course;

import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.model.StatusCourse;
import br.com.grupodois.plataformaCursos.repository.CourseRepository;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CourseUpdateForm {
    @NotNull  @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String category;
    @NotNull
    private double workload;
    @NotNull
    private StatusCourse status;
    @NotNull
    private boolean has_certificate;
    @NotNull
    private double evaluation;

    public Course update(Long id, CourseRepository courseRepository) {
        Course course = courseRepository.getById(id);
        course.setName(this.name);
        course.setCategory(this.category);
        course.setWorkload(this.workload);
        course.setStatus(this.status);
        course.setHas_certificate(this.has_certificate);
        course.setEvaluation(this.evaluation);
        return course;
    }
}
