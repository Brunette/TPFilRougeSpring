package dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	User findByUsername(String name);

	User findBySuperuserCinemaSallesIdIn(int salleid);
}