package api.java.backend.dto.cart;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdateCartDto {
    @Positive(message = "Se requiere un id del carrito")
    private Long id;
    @Positive(message = "El id de usuario no es válido")
    private Long user_id;
    @Positive(message = "El id de producto no es válido")
    private Long product_id;
    private int quantity;
}
