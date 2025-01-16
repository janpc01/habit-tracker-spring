package com.backend.tempo.services;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tempo.repositories.UserRepository;
import com.backend.tempo.database.User;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User createUser(User user) {
    log.info("Creating user: {}", user);
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    log.info("Getting all users");
    return userRepository.findAll();
  }

  public User getUserById(Integer id) {
    log.info("Getting user by id: {}", id);
    return userRepository.findById(id).orElse(null);
  }

  public User updateUser(Integer id, User user) {
    log.info("Updating user with id: {}", id);
    User existingUser = getUserById(id);
    if (existingUser != null) {
      existingUser.setEmail(user.getEmail());
      existingUser.setPassword(user.getPassword());
      return userRepository.save(existingUser);
    }
    return null;
  }

  public void deleteUser(Integer id) {
    log.info("Deleting user with id: {}", id);
    userRepository.deleteById(id);
  }

  public boolean userExists(Integer id) {
    log.info("Checking if user exists with id: {}", id);
    return userRepository.existsById(id);
  }
}