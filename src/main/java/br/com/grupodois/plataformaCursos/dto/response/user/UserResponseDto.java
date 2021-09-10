package br.com.grupodois.plataformaCursos.dto.response.user;

import br.com.grupodois.plataformaCursos.model.User;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
    }
}
