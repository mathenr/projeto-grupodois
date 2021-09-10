package br.com.grupodois.plataformaCursos.config.validation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Errors {
    List<String> errors;
}
