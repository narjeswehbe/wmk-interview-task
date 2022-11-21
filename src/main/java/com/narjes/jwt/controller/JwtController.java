package com.narjes.jwt.controller;

import com.narjes.jwt.requests.LoginRequest;
import com.narjes.jwt.response.jwtResponse;
import com.narjes.jwt.services.CustomUsersDetailsService;
import com.narjes.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class JwtController {

    private final  CustomUsersDetailsService customUsersDetailsService;
    private final  AuthenticationManager authenticationManager;
    private  final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest request)
    {
        //authenticate the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));
       UserDetails userdetails =  customUsersDetailsService.loadUserByUsername(request.getUsername());
      String s =   jwtUtil.generateToken(userdetails);
        jwtResponse response = new jwtResponse(s);
        return ResponseEntity.ok(response);

    }

}
