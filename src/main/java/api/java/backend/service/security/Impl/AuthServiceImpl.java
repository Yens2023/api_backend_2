package api.java.backend.service.security.Impl;

import api.java.backend.config.jwt.JwtTokenUtil;
import api.java.backend.domain.security.Role;
import api.java.backend.domain.security.User;
import api.java.backend.dto.auth.LoginRequestDto;
import api.java.backend.dto.auth.LoginResponseDto;
import api.java.backend.dto.user.CreateUserDto;
import api.java.backend.dto.user.ReadUserDto;
import api.java.backend.exception.auth.EmailAlreadyExistsException;
import api.java.backend.exception.auth.UsernameAlreadyExistsException;
import api.java.backend.exception.global.ResourceNotFoundException;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.security.UserRepository;
import api.java.backend.service.security.IAuthService;
import api.java.backend.service.security.IRoleService;
import api.java.backend.utils.SD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IRoleService roleService;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        return new LoginResponseDto(jwt);

    }

    @Override
    public ReadUserDto register(CreateUserDto request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("El nombre de usuario ya está en uso!");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("El correo electrónico ya está en uso!");
        }

        User user = AutoMapper.copyProperties(request,User.class);
        var UserFromDb = userRepository.save(user);
        // agregando rol user
        Role role = roleService.findByName(SD.USER_ROL);
        Set<Role> rolList = new HashSet<>();
        rolList.add(role);
        user.setRoles(rolList);

        userRepository.save(user);


        return AutoMapper.copyProperties(user, ReadUserDto.class);
    }

    @Override
    public UserDetails getUserByName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with username: " + username)
                );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }
}
