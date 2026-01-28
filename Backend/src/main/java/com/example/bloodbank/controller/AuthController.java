package com.example.bloodbank.controller;

import com.example.bloodbank.model.User;
import com.example.bloodbank.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    // TEST (VERY IMPORTANT)
    @GetMapping("/test")
    public String test() {
        return "Auth controller working";
    }
}
