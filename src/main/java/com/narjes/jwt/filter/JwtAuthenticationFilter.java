package com.narjes.jwt.filter;

import com.narjes.jwt.services.CustomUsersDetailsService;
import com.narjes.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//call this filter only once per request
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final  CustomUsersDetailsService customUsersDetailsService;
   private final  JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String username;
        String token;
        //get jwt token
        String bearer = request.getHeader("Authorization");
        //validate the token
        if(bearer!=null && bearer.startsWith("Bearer"))
        {
            token = bearer.substring(7);
            try{
                //extract username from token
                username = jwtUtil.extractUsername(token);
                //get user details
                UserDetails userDetails = customUsersDetailsService.loadUserByUsername(username);
                //security checks
                if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(upat);

                }else {
                    System.out.println("Invalid Token!!");
                }

            }catch(Exception e)
            {

            }

        }
        //if all is well forward the filter request to the request endpoint
        filterChain.doFilter(request, response);


    }
}
