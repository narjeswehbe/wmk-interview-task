package com.narjes.jwt.controller;

import com.narjes.jwt.entity.User;
import com.narjes.jwt.model.UserModel;
import com.narjes.jwt.repository.UserRepository;
import com.narjes.jwt.requests.LoginRequest;
import com.narjes.jwt.response.jwtResponse;
import com.narjes.jwt.services.CustomUsersDetailsService;
import com.narjes.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class JwtController {

    private final  CustomUsersDetailsService customUsersDetailsService;
    private final  AuthenticationManager authenticationManager;
    private  final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserModel userModel){
        UserModel userModel1 = customUsersDetailsService.register(userModel);
        return new ResponseEntity<>(userModel1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest request)
    {
        //authenticate the user
        System.out.println("Inside controller");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));

       UserDetails userdetails =  customUsersDetailsService.loadUserByUsername(request.getUsername());
        System.out.println(userdetails.getUsername());
        String s =   jwtUtil.generateToken(userdetails);
        jwtResponse response = new jwtResponse(s);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/currentUser")
    public UserModel getCurrentUser(Principal principal) {
        UserDetails userDetails =  this.customUsersDetailsService.loadUserByUsername(principal.getName());
        return (UserModel) userDetails;
    }

}
