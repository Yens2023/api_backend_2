package api.java.backend.dto.productimage;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdateProductImageDto {
    @Positive(message = "El id del producto no es válido")
    private Long id;
    private String description;
}
