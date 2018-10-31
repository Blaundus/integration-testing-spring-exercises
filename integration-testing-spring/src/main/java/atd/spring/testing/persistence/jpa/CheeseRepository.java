package atd.spring.testing.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atd.spring.testing.exchange.Cheese;



@Repository
public interface CheeseRepository extends JpaRepository<Cheese, Long> {

	public List<Cheese> findAll();
}
