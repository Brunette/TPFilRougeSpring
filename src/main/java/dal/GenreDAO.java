package dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {

}