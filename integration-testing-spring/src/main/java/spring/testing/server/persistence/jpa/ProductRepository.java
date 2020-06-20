package spring.testing.server.persistence.jpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.testing.server.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAll();
}
