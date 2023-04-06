package api.java.backend.repository.master;

import api.java.backend.domain.master.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String username);
    Boolean existsByNameAndIdNot(String username, Long id);
}
