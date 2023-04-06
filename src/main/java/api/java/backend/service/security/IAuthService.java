package api.java.backend.service.security;


import api.java.backend.dto.auth.LoginRequestDto;
import api.java.backend.dto.auth.LoginResponseDto;
import api.java.backend.dto.user.CreateUserDto;
import api.java.backend.dto.user.ReadUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthService {
    LoginResponseDto login (LoginRequestDto request);
    ReadUserDto register (CreateUserDto request);
    UserDetails getUserByName(String username);

}
