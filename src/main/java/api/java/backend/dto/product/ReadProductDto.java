package api.java.backend.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String brand;
}
