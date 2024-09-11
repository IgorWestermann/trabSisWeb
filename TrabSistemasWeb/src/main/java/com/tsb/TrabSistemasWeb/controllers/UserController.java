package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> GetUsers() {
        List<User> users = userService.Get();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> FindById(@PathVariable Integer id) {
        User user = userService.GetById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> Delete(@RequestParam Integer id) {
        userService.Delete(id);

        return ResponseEntity.ok().body("User successfully deleted!");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> Update(@PathVariable Integer id, @RequestBody User user) {
        User updatedUser = userService.Update(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }

}
