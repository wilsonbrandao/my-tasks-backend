package com.example.mytasksbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.save(userReq));
    }
}
