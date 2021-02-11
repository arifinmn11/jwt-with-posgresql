package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Users;
import com.arifinmn.projectapi.models.ResponseMessage;
import com.arifinmn.projectapi.models.users.UsersRequest;
import com.arifinmn.projectapi.models.users.UsersResponse;
import com.arifinmn.projectapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/user")
    public ResponseMessage<Users> signup(@RequestBody UsersRequest request) throws NoSuchAlgorithmException {

        String username = request.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new ValidationException("Username already existed");
        }

        String password = request.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        String fullname = request.getFullname();
        Users response = userRepository.save(new Users(username, encodedPassword, fullname));

        return ResponseMessage.success(response);
    }

    @GetMapping("/test")
    public String test() {
        return "ini test";
    }
}