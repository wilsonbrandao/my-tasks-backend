package com.example.mytasksbackend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserServiceInterface userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        //ambiente de dev
//        logger.trace("Trace log"); //nivel mais fino
//        logger.debug("debug log"); //info para o dev, degubando um codigo
//
//        logger.info("info log"); // equipe que mantem o software
//        logger.warn("warn log"); //eventos que não vão derrubar a app, mas são perigosos
//        logger.error("error log"); //eventos não fatais, mas que geram algum tipo de exceção

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isEmpty()) {
            logger.warn("User with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.save(userReq));
    }
}
