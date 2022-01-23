package com.example.mytasksbackend.user;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();
    User save(UserReq userReq);

}
