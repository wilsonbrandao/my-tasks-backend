package com.example.mytasksbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;
    private UserConfig userConfig;

    public UserService(UserRepository userRepository, UserConfig userConfig) {
        this.userRepository = userRepository;
        this.userConfig = userConfig;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserReq userReq) {
        if (userConfig.getMaxNumber() == userRepository.count()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Numero máximo de usuários atingido!");
        }
        if (userRepository.existsByEmail(userReq.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado!");
        }

        User user = new User(userReq.getName(), userReq.getEmail(), userReq.getPassword());
        return userRepository.save(user);
    }


}
