package api.java.backend.dto.brand;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateBrandDto {
    @Positive(message = "El id de la marca no es válido")
    private Long id;
    @NotBlank(message = "Falta completar la marca")
    @Size(min = 3, message = "La marca debe tener al menos 3 digitos")
    private String name;
}
