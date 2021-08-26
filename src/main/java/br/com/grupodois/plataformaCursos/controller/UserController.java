package br.com.grupodois.plataformaCursos.controller;

import br.com.grupodois.plataformaCursos.dto.form.user.UserFormDto;
import br.com.grupodois.plataformaCursos.dto.form.user.UserUpdateFormDto;
import br.com.grupodois.plataformaCursos.dto.response.user.UserErrorMessageDto;
import br.com.grupodois.plataformaCursos.dto.response.user.UserResponseDeleteDto;
import br.com.grupodois.plataformaCursos.dto.response.user.UserResponseDto;
import br.com.grupodois.plataformaCursos.model.User;
import br.com.grupodois.plataformaCursos.repository.UserRepository;
import br.com.grupodois.plataformaCursos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "users")
    public Page<UserResponseDto> findAll(@RequestParam(required = false) String firstName,
                                         @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pagination) {
        try {
            if (firstName == null) {
                return userRepository.findAll(pagination).map(UserResponseDto::new);
            } else {
                return userRepository.findByFirstNameLike(firstName, pagination).map(UserResponseDto::new);
            }
        } catch (ResponseStatusException exception) {
            return Page.empty();
        }

    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Cacheable(value = "user")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            UserResponseDto userResponse = userService.createResponseDto(id);
            return ResponseEntity.ok(userResponse);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().body(UserErrorMessageDto.builder().message("User not found!").build());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = {"users", "user"}, allEntries = true)
    public ResponseEntity<?> store(@RequestBody @Valid UserFormDto userForm) {
        try {
            User user = User.builder().firstName(
                            userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .email(userForm.getEmail())
                    .password(userForm.getPassword())
                    .build();
            User userResponse = userRepository.save(user);
            return ResponseEntity.ok(new UserResponseDto(userResponse));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().body(UserErrorMessageDto.builder().message("Ops! An error occurred to save user!").build());
        }
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CacheEvict(value = {"users", "user"}, allEntries = true)
    public ResponseEntity<?> update(@RequestBody UserUpdateFormDto userFormDto, @PathVariable Long id) {
        try {
            User userToUpdate = userService.getUserById(id);
            userToUpdate.setFirstName(userFormDto.getFirstName());
            userToUpdate.setLastName(userFormDto.getLastName());
            userToUpdate.setEmail(userFormDto.getEmail());
            User userUpdated = userRepository.save(userToUpdate);

            UserResponseDto userResponseDto = UserResponseDto.builder()
                    .id(userUpdated.getId())
                    .firstName(userUpdated.getFirstName())
                    .lastName(userUpdated.getLastName())
                    .email(userUpdated.getEmail())
                    .createdAt(userUpdated.getCreatedAt())
                    .updatedAt(userUpdated.getUpdatedAt())
                    .build();
            return ResponseEntity.ok(userResponseDto);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().body(UserErrorMessageDto.builder().message("Ops! An error occurred to update user!").build());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CacheEvict(value = {"users", "user"}, allEntries = true)
    public ResponseEntity<?> delete(@PathVariable() Long id) {
        try {
            User user = userService.getUserById(id);
            userRepository.delete(user);
            UserResponseDeleteDto userDeleted = UserResponseDeleteDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .build();
            return ResponseEntity.ok(userDeleted);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.badRequest().body(UserErrorMessageDto.builder().message("Ops! An error occurred to delete user!").build());
        }
    }
}