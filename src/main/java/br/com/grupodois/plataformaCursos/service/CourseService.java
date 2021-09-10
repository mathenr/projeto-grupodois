package br.com.grupodois.plataformaCursos.service;

import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 25/08/2021
 */
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        try {
            Optional<Course> user = courseRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (ResponseStatusException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
