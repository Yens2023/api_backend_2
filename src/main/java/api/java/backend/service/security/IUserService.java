package api.java.backend.service.security;

import api.java.backend.domain.security.User;
import api.java.backend.dto.user.CreateUserDto;
import api.java.backend.dto.user.UpdateUserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
// spring repository methods

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(CreateUserDto createUser);
    User update(UpdateUserDto updateUser);

    void deleteById(Long id);


    //custom methods
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
