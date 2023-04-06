package api.java.backend.dto.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@Getter
@Setter
public class UpdateProductDto {
    @Positive(message = "El id del producto no es válido")
    private Long id;
    @NotBlank(message = "Falta completar el nombre del producto")
    @Size(min = 3, message = "El nombre debe tener al menos 2 digitos")
    private String name;
    @NotBlank(message = "Falta completar la descripción del producto")
    @Size(min = 10, message = "La descripción debe tener al menos 10 digitos")
    private String description;
    @Positive(message = "El precio no puede ser cero o negativo")
    private Double price;
    @Positive(message = "El stock no puede ser cero o negativo")
    private Integer stock;
    @Positive(message = "El id de la categoria no es válido")
    private Long category_id;
    @Positive(message = "El id de la marca no es válido")
    private Long brand_id;
}
