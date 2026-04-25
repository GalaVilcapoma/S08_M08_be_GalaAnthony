package pe.edu.vallegrande.eduSmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.eduSmart.model.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByAvailableTrue();

    @Query(value = "SELECT * FROM games WHERE id = ?1", nativeQuery = true)
    Optional<Game> findByIdIncludingDeleted(Long id);

    @Query(value = "SELECT * FROM games", nativeQuery = true)
    List<Game> findAllIncludingDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.available = :status WHERE g.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    @Query("SELECT g FROM Game g WHERE g.available = true AND " +
            "(LOWER(g.title) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(g.description) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(g.developer) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Game> searchByQuery(@Param("q") String q);

    List<Game> findByPlatformIgnoreCaseAndAvailableTrue(String platform);

    @Query("SELECT g FROM Game g WHERE g.available = true ORDER BY g.rating DESC")
    List<Game> findTopRated();
}
