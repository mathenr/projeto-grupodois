package br.com.grupodois.plataformaCursos.controller;

import br.com.grupodois.plataformaCursos.dto.form.subscription.SubscriptionFormDto;
import br.com.grupodois.plataformaCursos.dto.response.subscription.SubscriptionResponseDto;
import br.com.grupodois.plataformaCursos.model.Status;
import br.com.grupodois.plataformaCursos.model.Subscription;
import br.com.grupodois.plataformaCursos.model.User;
import br.com.grupodois.plataformaCursos.model.Course;
import br.com.grupodois.plataformaCursos.repository.SubscriptionRepository;
import br.com.grupodois.plataformaCursos.service.CourseService;
import br.com.grupodois.plataformaCursos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SubscriptionResponseDto> findAll(@RequestParam(required = false) Long courseId, Pageable pagination) {
        try {
            return subscriptionRepository.findAll(pagination).map(subscription ->
                    SubscriptionResponseDto.builder().id(subscription.getId())
                            .course(subscription.getCourse()).user(subscription.getUser())
                            .progress(subscription.getProgress()).status(subscription.getStatus())
                            .createdAt(subscription.getCreatedAt()).updatedAt(subscription.getUpdatedAt()).build());
        } catch (ResponseStatusException exception) {
            return Page.empty();
        }
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public Page<SubscriptionResponseDto> findByCourseId(@RequestParam(name = "courseId") Long courseId, Pageable pagination) {
        try {
            return subscriptionRepository.findByCourseId(courseId, pagination).map(subscription ->
                    SubscriptionResponseDto.builder().id(subscription.getId())
                            .course(subscription.getCourse()).user(subscription.getUser())
                            .progress(subscription.getProgress()).status(subscription.getStatus())
                            .createdAt(subscription.getCreatedAt()).updatedAt(subscription.getUpdatedAt()).build()
            );
        } catch (ResponseStatusException exception) {
            return Page.empty();
        }
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<SubscriptionResponseDto> findByUserId(@RequestParam(name = "userId") Long courseId, Pageable pagination) {
        try {
            return subscriptionRepository.findByUserId(courseId, pagination).map(subscription ->
                    SubscriptionResponseDto.builder().id(subscription.getId())
                            .course(subscription.getCourse()).user(subscription.getUser())
                            .progress(subscription.getProgress()).status(subscription.getStatus())
                            .createdAt(subscription.getCreatedAt()).updatedAt(subscription.getUpdatedAt()).build());
        } catch (ResponseStatusException exception) {
            return Page.empty();
        }
    }

    @GetMapping("/{userId}/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findByUserIdAndCourseId(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        try {
            Subscription subscription = subscriptionRepository.findByUserIdAndCourseId(userId, courseId);
            return ResponseEntity.ok(SubscriptionResponseDto.builder().id(subscription.getId())
                    .course(subscription.getCourse()).user(subscription.getUser())
                    .progress(subscription.getProgress()).status(subscription.getStatus())
                    .createdAt(subscription.getCreatedAt()).updatedAt(subscription.getUpdatedAt()).build()
            );
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> store(@RequestBody SubscriptionFormDto subscriptionFormDto) {
        try {
            User user = userService.getUserById(subscriptionFormDto.getUserId());
            System.out.println("passei aqui");
            Course course = courseService.getCourseById(subscriptionFormDto.getCourseId());
            Subscription subscription = Subscription.builder().course(course).user(user).progress(new BigDecimal("0.0")).status(Status.IN_PROGRESS).build();
            Subscription subscriptionSaved = subscriptionRepository.save(subscription);
            SubscriptionResponseDto subscriptionResponseDto = SubscriptionResponseDto.builder()
                    .course(subscriptionSaved.getCourse()).user(subscriptionSaved.getUser())
                    .progress(subscriptionSaved.getProgress()).status(subscriptionSaved.getStatus())
                    .build();
            return ResponseEntity.ok(subscriptionResponseDto);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
