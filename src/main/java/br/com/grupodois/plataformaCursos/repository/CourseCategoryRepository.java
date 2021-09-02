package br.com.grupodois.plataformaCursos.repository;

import br.com.grupodois.plataformaCursos.model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

}
