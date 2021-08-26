package br.com.grupodois.plataformaCursos.dto.form.course;

import br.com.grupodois.plataformaCursos.model.Course;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CourseForm {
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String category;
    @NotNull
    private double workload;
    @NotNull @NotEmpty
    private String status;
    @NotNull
    private boolean has_certificate;
    @NotNull
    private double evaluation;

    public Course convert(){
        return new Course(name, category, workload, status, has_certificate, evaluation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHas_certificate() {
        return has_certificate;
    }

    public void setHas_certificate(boolean has_certificate) {
        this.has_certificate = has_certificate;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
}
