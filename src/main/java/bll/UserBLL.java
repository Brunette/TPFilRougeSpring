package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.User;
import dal.UserDAO;

@Service
public class UserBLL {
	@Autowired
	private UserDAO dao;

	public UserBLL() {
	}

	public List<User> selectAll() {
		return dao.findAll();
	}

	public User selectById(int id) {
		return dao.findById(id).get();
	}

	public User selectByUsername(String name) {
		return dao.findByUsername(name);
	}

	public User selectBySalleID(int salleid) {
		return dao.findBySuperuserCinemaSallesIdIn(salleid);
	}

	public void insert(User user) {
		dao.save(user);
	}

	public void update(User user) {
		dao.save(user);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

}