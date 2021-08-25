package br.com.grupodois.plataformaCursos.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name cannot be empty!") @NotNull(message = "First name required!")
    @Column(nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!") @NotNull(message = "Last name required!")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty(message = "E-mail cannot be empty!") @NotNull(message = "E-mail required!")
    @Column
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    @Column(nullable = false)
    private String password;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @OrderColumn
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }
    @Override
    public int hashCode() {
        return 562048007;
    }
}
