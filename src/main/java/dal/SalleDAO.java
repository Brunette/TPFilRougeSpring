package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Salle;

@Repository
public interface SalleDAO extends JpaRepository<Salle, Integer> {
	public List<Salle> findByCinemaId(int cinemaid);
}