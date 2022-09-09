package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Reservation;
import bo.User;
import dal.ReservationDAO;

@Service
public class ReservationBLL {
	@Autowired
	private ReservationDAO dao;

	public ReservationBLL() {
	}

	public List<Reservation> selectAll() {
		return dao.findAll();
	}

	public Reservation selectById(int id) {
		return dao.findById(id).get();
	}

	public List<Reservation> selectByUser(User user) {
		return dao.findByUser(user);
	}

	public void update(Reservation reservation) {
		dao.save(reservation);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public void insert(Reservation reservation) {
		dao.save(reservation);
	}
}
