package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/userController")
public class UserController {

    private UserService userService;

    private RestTemplate restTemplate;

    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService =userService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.validateAndSaveUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getUsers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userService.getUser(id).get(), HttpStatus.OK);
    }


}
