package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Reservation;
import bo.User;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

	List<Reservation> findByUser(User user);

}