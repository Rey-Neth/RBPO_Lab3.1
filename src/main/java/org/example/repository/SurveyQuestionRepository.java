package org.example.repository;

import org.example.model.SurveyQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    @Override
    @Query("select e from SurveyQuestion e order by e.id")
    List<SurveyQuestion> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from SurveyQuestion e where e.id = :id")
    Optional<SurveyQuestion> findByIdForUpdate(@Param("id") Long id);

    // Bulk SQL delegates dependent-row removal to PostgreSQL ON DELETE CASCADE.
    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from survey_questions where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query("select e from SurveyQuestion e where e.surveyId = :id order by e.id")
    List<SurveyQuestion> findBySurveyId(@Param("id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from survey_questions where survey_id = :id", nativeQuery = true)
    void deleteBySurveyId(@Param("id") Long id);
}
