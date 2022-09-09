package dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;

@Repository
public interface SeanceDAO extends JpaRepository<Seance, Integer> {

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c = ?1 ORDER BY f.name,TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinema(Cinema cinema);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c = ?1 AND DATE(sc.heureSeance) = DATE(?2) ORDER BY f.name,TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinemaAndDay(Cinema cinema, LocalDate date);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as salle LEFT JOIN salle.cinema as cinema WHERE cinema = ?1 AND sc.film = ?2 AND DATE(sc.heureSeance) = DATE(?3) ORDER BY sc.film.name, TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinemaAndFilmAndDay(Cinema cinema, Film film, LocalDate date);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c where c = ?1 AND sc.film = ?2 ORDER BY sc.film.name ASC")
	public List<Seance> findByCinemaAndFilm(Cinema cinema, Film film);

	public List<Seance> findBySalle(Salle salle);
}
