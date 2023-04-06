package api.java.backend.repository.master;


import api.java.backend.domain.master.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Boolean existsByName(String username);
    Boolean existsByNameAndIdNot(String username, Long id);
}
