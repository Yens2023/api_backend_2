package api.java.backend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDto {
    @Positive(message = "El id de la categoria no es válido")
    private Long id;
    @NotBlank(message = "Falta completar la categoria")
    @Size(min = 3, message = "La categoria debe tener al menos 3 digitos")
    private String name;
}
