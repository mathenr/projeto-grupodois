package br.com.grupodois.plataformaCursos.dto.response.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDeleteDto {
    private Long id;
    private String email;
}
