package br.com.grupodois.plataformaCursos.dto.form.user;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {

    @NotEmpty(message = "First name cannot be empty!") @NotNull(message = "First name required!") @Length(max = 40)
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!") @NotNull(message = "Last name required!") @Length(max = 40)
    private String lastName;

    @Email(message = "Please, insert a valid e-mail!")
    @Length(max = 65, message = "Tamanho máximo de 65 letras!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!") @NotNull(message = "Password required!") @Length(min = 6, max = 40)
    private String password;
}
