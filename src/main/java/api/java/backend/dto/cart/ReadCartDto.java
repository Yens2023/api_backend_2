package api.java.backend.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadCartDto {
    private Long id;
    private String producto;
    private double price;
    private int quantity;
    private double total;
}
