package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Salle;
import dal.SalleDAO;

@Service
public class SalleBLL {
	@Autowired
	private SalleDAO dao;

	public SalleBLL() {
	}

	public List<Salle> selectByCinema(int cinemaid) {
		return dao.findByCinemaId(cinemaid);
	}

	public Salle selectById(int id) {
		return dao.findById(id).get();
	}
}
