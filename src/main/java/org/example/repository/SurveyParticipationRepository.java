package org.example.repository;

import org.example.model.SurveyParticipation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SurveyParticipationRepository extends JpaRepository<SurveyParticipation, Long> {
    @Override
    @Query("select e from SurveyParticipation e order by e.id")
    List<SurveyParticipation> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from SurveyParticipation e where e.id = :id")
    Optional<SurveyParticipation> findByIdForUpdate(@Param("id") Long id);

    // Bulk SQL delegates dependent-row removal to PostgreSQL ON DELETE CASCADE.
    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from survey_participations where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query("select e from SurveyParticipation e where e.surveyId = :id order by e.id")
    List<SurveyParticipation> findBySurveyId(@Param("id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from survey_participations where survey_id = :id", nativeQuery = true)
    void deleteBySurveyId(@Param("id") Long id);

    @Query("select e from SurveyParticipation e where e.respondentId = :id order by e.id")
    List<SurveyParticipation> findByRespondentId(@Param("id") Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from survey_participations where respondent_id = :id", nativeQuery = true)
    void deleteByRespondentId(@Param("id") Long id);
}
