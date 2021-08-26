package br.com.grupodois.plataformaCursos.dto.form.subscription;

import lombok.*;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class SubscriptionFormDto {
    private Long userId;
    private Long courseId;
}
