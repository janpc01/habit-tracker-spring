package com.backend.tempo.controllers;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.tempo.services.UserService;
import com.backend.tempo.database.User;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    log.info("Getting all users");
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Integer id) {
    log.info("Getting user by id: {}", id);
    User user = userService.getUserById(id);
    if (user != null) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    log.info("Creating user: {}", user);
    User createdUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
    log.info("Updating user with id: {}", id);
    User updatedUser = userService.updateUser(id, user);
    if (updatedUser != null) {
      return ResponseEntity.ok(updatedUser);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    log.info("Deleting user with id: {}", id);
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists/{id}")
  public ResponseEntity<Boolean> userExists(@PathVariable Integer id) {
    boolean exists = userService.userExists(id);
    log.info("Checking if user exists with id: {}", id);
    return ResponseEntity.ok(exists);
  }
}

