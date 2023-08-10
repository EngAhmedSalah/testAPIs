package com.example.testapis.controller;

import com.example.testapis.exception.UserNotFoundException;
import com.example.testapis.model.UserModel;
import com.example.testapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public UserModel addUser(@RequestBody UserModel user)
    {
        userService.save(user);
        return user;
    }
    @GetMapping("/users")
    public List<UserModel> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @GetMapping("/user")
    public Optional<UserModel> getUser(@RequestBody UserModel userModel) throws UserNotFoundException {
        return userService.getUser(userModel);
    }
    @DeleteMapping("/user")
    public UserModel deleteUser(@RequestBody UserModel userModel) throws UserNotFoundException {
        return userService.deleteUser(userModel);
    }
}
