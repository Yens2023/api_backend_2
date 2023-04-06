package api.java.backend.repository.master;

import api.java.backend.domain.master.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
