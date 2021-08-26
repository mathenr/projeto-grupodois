package br.com.grupodois.plataformaCursos.repository;

import br.com.grupodois.plataformaCursos.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Page<Subscription> findByCourseId(Long id, Pageable pagination);
    Page<Subscription> findByUserId(Long id, Pageable pagination);
    Subscription findByUserIdAndCourseId(Long userId, Long courseId);
}
