package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Cinema;
import dal.CinemaDAO;

@Service
public class CinemaBLL {
	@Autowired
	private CinemaDAO dao;

	public CinemaBLL() {
	}

	public List<Cinema> selectAll() {
		return dao.findAll();
	}

	public Cinema selectById(int id) {
		return dao.findById(id).get();
	}

	void insert(Cinema cinema) {
		dao.save(cinema);
	}

	void update(Cinema cinema) {
		dao.save(cinema);
	}

	void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Cinema> selectByCritere(String filter) {
		if (!filter.isBlank())
			return dao.findByAddressVilleStartsWithOrAddressAddress1StartsWithOrAddressCodePostalStartsWith(filter,
					filter, filter);
		else
			return dao.findAll();
	}

}
