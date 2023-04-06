package api.java.backend.controller.v1;

import api.java.backend.dto.auth.LoginRequestDto;
import api.java.backend.dto.auth.LoginResponseDto;
import api.java.backend.dto.custom.APIResponse;
import api.java.backend.dto.user.CreateUserDto;
import api.java.backend.dto.user.ReadUserDto;
import api.java.backend.service.security.IAuthService;
import api.java.backend.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private  final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login (@RequestBody @Valid LoginRequestDto loginRequest){
        var userDto = authService.login(loginRequest);

        APIResponse responseDto = APIResponse
                .<LoginResponseDto>builder()
                .status(SD.SUCCESS)
                .results(userDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateUserDto request){
        var userDto = authService.register(request);

        APIResponse<ReadUserDto> responseDto  = APIResponse
                .<ReadUserDto>builder()
                .status(SD.SUCCESS)
                .results(userDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

}
