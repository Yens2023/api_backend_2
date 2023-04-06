package api.java.backend.dto.brand;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateBrandDto {
    @NotBlank(message = "Falta completar la marca")
    @Size(min = 3, message = "La marca debe tener al menos 3 digitos")
    private String name;
}
