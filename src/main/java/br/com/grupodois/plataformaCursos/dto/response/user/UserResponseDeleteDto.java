package br.com.grupodois.plataformaCursos.dto.response.user;

import lombok.*;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDeleteDto {
    private Long id;
    private String email;
}
