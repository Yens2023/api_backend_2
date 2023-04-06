package api.java.backend.repository.master;

import api.java.backend.domain.master.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    List<ProductImages> findByProduct_Id(Long productId);
}
