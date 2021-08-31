package br.com.grupodois.plataformaCursos.dto.form.course;

import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.model.StatusCourse;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseForm {
    @NotNull @NotEmpty
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

    public Course convert(){
        return  new Course().builder().name(name).category(category).workload(workload).status(status).has_certificate(has_certificate).evaluation(evaluation).build();
    }

}
