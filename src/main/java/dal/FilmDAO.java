package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.Film;

@Repository
public interface FilmDAO extends JpaRepository<Film, Integer> {
	List<Film> findByGenresNameIn(List<String> names);

	@Query(value = "SELECT f FROM Seance sc JOIN sc.salle sl JOIN sl.cinema c JOIN sc.film f WHERE c.id = ?1 GROUP BY f")
	List<Film> findByCinemaId(int cinemaid);
}