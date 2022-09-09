package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Cinema;

@Repository
public interface CinemaDAO extends JpaRepository<Cinema, Integer> {
	List<Cinema> findByAddressVilleStartsWithOrAddressAddress1StartsWithOrAddressCodePostalStartsWith(String filter1,
			String filter2, String filter3);
}
