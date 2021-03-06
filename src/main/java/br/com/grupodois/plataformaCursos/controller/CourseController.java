package br.com.grupodois.plataformaCursos.controller;

import br.com.grupodois.plataformaCursos.dto.CourseDto;
import br.com.grupodois.plataformaCursos.dto.form.course.CourseForm;
import br.com.grupodois.plataformaCursos.dto.form.course.CourseUpdateForm;
import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "courses")
    public Page<CourseDto> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0)
                                               Pageable pagination){
        Page<Course> courses = courseRepository.findAll(pagination);

        return CourseDto.convert(courses);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "course")
    public ResponseEntity<CourseDto> findOne(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return ResponseEntity.ok(new CourseDto(course.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = {"courses", "course"}, allEntries = true)
    public ResponseEntity<CourseDto> store(@RequestBody @Valid CourseForm courseForm, UriComponentsBuilder uriBuilder){
        Course course = courseForm.convert();
        courseRepository.save(course);

        URI uri = uriBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(new CourseDto(course));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = {"courses", "course"}, allEntries = true)
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
    @CacheEvict(value = {"courses", "course"}, allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
