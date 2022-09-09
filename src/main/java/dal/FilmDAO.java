package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Film;

@Repository
public interface FilmDAO extends JpaRepository<Film, Integer> {
	List<Film> findByGenresNameIn(List<String> names);
}