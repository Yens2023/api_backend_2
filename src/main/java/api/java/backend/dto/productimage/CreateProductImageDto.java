package api.java.backend.dto.productimage;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CreateProductImageDto {
    @NotBlank(message = "Falta completar la url de la imagen")
    private String url;
    private String description;
    @Positive(message = "Falta seleccionar el producto")
    private Long product_id;
}
