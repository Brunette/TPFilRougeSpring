package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {
	@Query(value = "SELECT g.id, g.genre_name FROM genres g LEFT JOIN asso_genres_films a on a.genre_id = g.id where a.filmId != '' GROUP BY g.genre_name", nativeQuery = true)
	List<Genre> findUsed();
}