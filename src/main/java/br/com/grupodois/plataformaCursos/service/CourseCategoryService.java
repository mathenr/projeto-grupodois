package br.com.grupodois.plataformaCursos.service;

import br.com.grupodois.plataformaCursos.model.CourseCategory;
import br.com.grupodois.plataformaCursos.repository.CourseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class CourseCategoryService {

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    public CourseCategory getCourseCategoryById(Long id) {
        try {
            Optional<CourseCategory> courseCategory = courseCategoryRepository.findById(id);
            if (courseCategory.isPresent()) {
                return courseCategory.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (ResponseStatusException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
