package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Cinema;
import bo.Film;
import dal.FilmDAO;

@Service
public class FilmBLL {
	@Autowired
	private FilmDAO dao;

	public FilmBLL() {
	}

	public List<Film> selectAll() {
		return dao.findAll();
	}

	public Film selectById(int id) {
		return dao.findById(id).get();
	}

	public void insert(Film film) {
		dao.save(film);
	}

	public void update(Film film) {
		dao.save(film);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Film> selectByGenre(List<String> genreNames) {
		return dao.findByGenresNameIn(genreNames);
	}

	public List<Film> selectByCinema(Cinema cinema) {
		// return dao.selectByCinema(cinema);
		return null;
	}
}