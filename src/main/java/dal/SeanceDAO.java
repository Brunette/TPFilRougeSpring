package dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;

@Repository
public interface SeanceDAO extends JpaRepository<Seance, Integer> {

	public List<Seance> findByCinema(Cinema cinema);

	public List<Seance> findByCinemaAndDay(Cinema cinema, LocalDate date);

	public List<Seance> findByCinemaAndFilmAndDay(Cinema cinema, Film film, LocalDate date);

	public List<Seance> findByCinemaIdAndFilm(int cinemaid, Film film);

	public List<Seance> findBySalle(Salle salle);
}
