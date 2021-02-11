package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.models.ResponseMessage;
import com.arifinmn.projectapi.security.JwtToken;
import com.arifinmn.projectapi.models.JwtRequest;
import com.arifinmn.projectapi.models.JwtResponse;
import com.arifinmn.projectapi.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseMessage<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService

                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtToken.generateToken(userDetails);

        return ResponseMessage.success(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

}