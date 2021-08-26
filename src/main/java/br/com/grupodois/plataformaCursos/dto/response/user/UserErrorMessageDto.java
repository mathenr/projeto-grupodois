package br.com.grupodois.plataformaCursos.dto.response.user;

import lombok.*;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class UserErrorMessageDto {
    private String message;
}
