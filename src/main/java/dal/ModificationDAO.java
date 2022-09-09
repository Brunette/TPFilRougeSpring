package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Modification;

@Repository
public interface ModificationDAO extends JpaRepository<Modification, Integer> {
	List<Modification> findBySalleCinemaId(Integer id);
}