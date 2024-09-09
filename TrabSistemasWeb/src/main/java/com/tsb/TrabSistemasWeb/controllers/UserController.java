package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> GetUser() {
        return ResponseEntity.ok("Success!");
    }

    @GetMapping(value = "list")
    public ResponseEntity<List<User>> GetUsers() {
        List<User> users = userService.Get();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User user = userService.GetById(id);
        return ResponseEntity.ok().body(user);
    }

}
