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

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c.id = ?1 ORDER BY f.name,TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinemaId(int cinemaid);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c.id = ?1 AND DATE(sc.heureSeance) = ?2 ORDER BY f.name,TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinemaIdAndDay(int cinemaid, LocalDate date);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as salle LEFT JOIN salle.cinema as cinema WHERE cinema = ?1 AND sc.film.id = ?2 AND DATE(sc.heureSeance) = DATE(?3) ORDER BY sc.film.name, TIME(sc.heureSeance) ASC")
	public List<Seance> findByCinemaAndFilmIdAndDay(Cinema cinema, int filmid, LocalDate date);

	@Query(value = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c where c.id = ?1 AND sc.film.id = ?2 ORDER BY sc.film.name ASC")
	public List<Seance> findByCinemaIdAndFilmId(int cinemaid, int filmid);

	default List<Seance> findByCinemaAndFilmAndDay(Cinema cinema, Film film, LocalDate date) {
		return findByCinemaAndFilmIdAndDay(cinema, film.getId(), date);
	}

	default List<Seance> findByCinemaIdAndFilm(int cinemaid, Film film) {
		return findByCinemaIdAndFilmId(cinemaid, film.getId());
	}

	default List<Seance> findByCinemaAndDay(Cinema cinema, LocalDate date) {
		return findByCinemaIdAndDay(cinema.getId(), date);
	}

	default List<Seance> findByCinema(Cinema cinema) {
		return findByCinemaId(cinema.getId());
	}

	default List<Seance> findByCinemaFilm(Cinema cinema, Film film) {
		return findByCinemaIdAndFilmId(cinema.getId(), film.getId());
	}

	public List<Seance> findBySalle(Salle salle);

}
