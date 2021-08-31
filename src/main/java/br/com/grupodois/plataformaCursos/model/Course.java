package br.com.grupodois.plataformaCursos.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private double workload;
    @Enumerated(EnumType.STRING)
    private StatusCourse status;
    private boolean has_certificate;
    private double evaluation;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

}
