package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Genre;
import dal.GenreDAO;

@Service
public class GenreBLL {
	@Autowired
	private GenreDAO dao;

	public GenreBLL() {
	}

	public List<Genre> selectUsed() {
		// TODO: find only Genre with film associaces
		return dao.findAll();
	}
}
