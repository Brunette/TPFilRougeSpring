package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Modification;
import dal.ModificationDAO;

@Service
public class ModificationBLL {
	@Autowired
	private ModificationDAO dao;

	public ModificationBLL() {
	}

	public List<Modification> selectByCinemaID(int cinemaid) {
		return dao.findBySalleCinemaId(cinemaid);
	}

	public Modification selectById(int id) {
		return dao.findById(id).get();
	}

	public void insert(Modification modification) {
		dao.save(modification);

	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
