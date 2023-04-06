package api.java.backend.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {
    @NotBlank(message = "el usuario no puede estar vacio")
    private String username;
    @NotBlank(message = "la contraseña no puede estar vacia")
    protected String password;
}
