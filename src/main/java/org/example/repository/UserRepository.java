package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @Query("select e from User e order by e.id")
    List<User> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from User e where e.id = :id")
    Optional<User> findByIdForUpdate(@Param("id") Long id);

    // Bulk SQL delegates dependent-row removal to PostgreSQL ON DELETE CASCADE.
    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "delete from app_users where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
