package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Users;
import com.arifinmn.projectapi.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (userRepository.existsByUsername(username)) {
            throw new ValidationException("Username already existed");
        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        String fullname = body.get("fullname");
        userRepository.save(new Users(username, encodedPassword, fullname));
        return true;
    }

    @GetMapping("/test")
    public String test() {
        return "ini test";
    }
}