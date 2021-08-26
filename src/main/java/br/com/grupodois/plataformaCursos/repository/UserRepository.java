package br.com.grupodois.plataformaCursos.repository;

import br.com.grupodois.plataformaCursos.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

/**
 * @author Matheus Henrique da Silva Mendes
 * @version v0.1
 * @since 24/08/2021
 */

public interface UserRepository extends JpaRepository<User, Long>{
    Page<User> findByFirstNameLike(String name, Pageable pagination);
}
