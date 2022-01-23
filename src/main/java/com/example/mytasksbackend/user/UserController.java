package com.example.mytasksbackend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserServiceInterface userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.save(userReq));
    }
}
