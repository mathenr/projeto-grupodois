package br.com.grupodois.plataformaCursos.service;

import br.com.grupodois.plataformaCursos.dto.response.user.UserResponseDto;
import br.com.grupodois.plataformaCursos.model.User;
import br.com.grupodois.plataformaCursos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDto createResponseDto(Long id) {
        try {
            User user = getUserById(id);
            return new UserResponseDto(user);
        } catch (ResponseStatusException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    public User getUserById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
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
