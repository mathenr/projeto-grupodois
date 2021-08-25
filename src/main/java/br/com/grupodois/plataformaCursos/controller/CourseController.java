package br.com.grupodois.plataformaCursos.controller;

import br.com.grupodois.plataformaCursos.dto.CourseDto;
import br.com.grupodois.plataformaCursos.dto.form.course.CourseForm;
import br.com.grupodois.plataformaCursos.dto.form.course.CourseUpdateForm;
import br.com.grupodois.plataformaCursos.modelo.Course;
import br.com.grupodois.plataformaCursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<CourseDto> listAll(){
        List<Course> courses = courseRepository.findAll();

        return CourseDto.converter(courses);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CourseDto> create(@RequestBody @Valid CourseForm courseForm, UriComponentsBuilder uriBuilder){
        Course course = courseForm.converter();
        courseRepository.save(course);

        URI uri = uriBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(new CourseDto(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> list(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return ResponseEntity.ok(new CourseDto(course.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody @Valid CourseUpdateForm courseUpdateForm){
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isPresent()){
            Course course = courseUpdateForm.update(id, courseRepository);
            return ResponseEntity.ok(new CourseDto(course));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
