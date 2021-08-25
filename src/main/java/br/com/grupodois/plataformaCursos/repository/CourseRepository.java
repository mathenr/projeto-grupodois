package br.com.grupodois.plataformaCursos.repository;

import br.com.grupodois.plataformaCursos.modelo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
