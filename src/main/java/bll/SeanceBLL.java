package bll;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;
import dal.SeanceDAO;

@Service
public class SeanceBLL {
	@Autowired
	private SeanceDAO dao;

	public SeanceBLL() {
	}

	public List<Seance> selectAll() {
		return dao.findAll();
	}

	public List<Seance> selectByCinema(Cinema cinema) {
		return dao.findByCinema(cinema);
	}

	public List<Seance> selectByCinemaAndDay(Cinema cinema, LocalDate date) {
		return dao.findByCinemaAndDay(cinema, date);
	}

	public List<Seance> selectByCinemaFilmDay(Cinema cinema, Film film, LocalDate date) {
		return dao.findByCinemaAndFilmAndDay(cinema, film, date);
	}

	public List<Seance> selectByCinemaFilm(int cinemaid, Film film) {
		return dao.findByCinemaIdAndFilm(cinemaid, film);
	}

	public void insert(Seance seance) {
		dao.save(seance);
	}

	public void update(Seance seance) {
		dao.save(seance);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Seance> selectBySalle(Salle salle) {
		return dao.findBySalle(salle);
	}

	public Seance selectById(int intValue) {
		return dao.findById(intValue).get();
	}
}
