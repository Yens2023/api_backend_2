package api.java.backend.service.security.Impl;

import api.java.backend.domain.security.User;
import api.java.backend.dto.user.CreateUserDto;
import api.java.backend.dto.user.UpdateUserDto;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.security.UserRepository;
import api.java.backend.service.security.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(CreateUserDto createUser) {
        User user = AutoMapper.copyProperties(createUser,User.class);
        var UserFromDb = userRepository.save(user);
        return  UserFromDb;
    }

    @Override
    public User update(UpdateUserDto updateUser) {
        User UserFromDb = userRepository.findById(updateUser.getId()).get();
        BeanUtils.copyProperties(updateUser, UserFromDb);
        userRepository.save(UserFromDb);
        return UserFromDb;
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
