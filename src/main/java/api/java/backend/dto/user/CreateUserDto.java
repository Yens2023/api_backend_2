package api.java.backend.dto.user;

import api.java.backend.annotation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {

    @NotBlank(message = "el usuario no puede estar vacio")
    @Size(min = 8, message = "el nombre de usuario debe tener al menos 8 digitos")
    private String username;

    @NotBlank(message = "el correo electronico no puede estar vacio")
    @Email(message = "el correo eletronico no es valido")
    private String email;

    @ValidPassword()
    private String password;

}
