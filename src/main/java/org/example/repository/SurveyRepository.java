package org.example.repository;

import org.example.model.Survey;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Override
    @Query("select e from Survey e order by e.id")
    List<Survey> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Survey e where e.id = :id")
    Optional<Survey> findByIdForUpdate(@Param("id") Long id);

    // Bulk SQL delegates dependent-row removal to PostgreSQL ON DELETE CASCADE.
    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from surveys where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query("select e from Survey e where e.authorId = :id order by e.id")
    List<Survey> findByAuthorId(@Param("id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from surveys where author_id = :id", nativeQuery = true)
    void deleteByAuthorId(@Param("id") Long id);
}
