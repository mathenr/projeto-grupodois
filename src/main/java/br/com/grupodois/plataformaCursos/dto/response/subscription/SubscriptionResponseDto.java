package br.com.grupodois.plataformaCursos.dto.response.subscription;


import br.com.grupodois.plataformaCursos.model.Status;
import br.com.grupodois.plataformaCursos.model.User;
import br.com.grupodois.plataformaCursos.model.Course;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponseDto {

    private Long id;
    private Course course;
    private User user;
    private BigDecimal progress;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
