package spring.testing.server.persistence.jpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.testing.server.entities.Cheese;


@Repository
public interface CheeseRepository extends JpaRepository<Cheese, Long> {

	public List<Cheese> findAll();
}
