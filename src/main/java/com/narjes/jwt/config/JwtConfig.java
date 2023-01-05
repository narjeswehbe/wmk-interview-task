package com.narjes.jwt.config;

import com.narjes.jwt.filter.JwtAuthenticationFilter;
import com.narjes.jwt.services.CustomUsersDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtConfig extends WebSecurityConfigurerAdapter {
    private final CustomUsersDetailsService customUsersDetailsService;
    private final JwtAuthenticationFilter jwtFilter;
    private final jwtAuthEntryPoint jwtAuthEntryPoint;
    //control which end points are permitted
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.headers().frameOptions().disable();
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                 .antMatchers("/api/login", "/api/register").permitAll()//only allow this endpoint without authentication
                .anyRequest().authenticated()//for any other request, authentication should performed
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//every request should be independent of other and server does not have to manage session

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }



    // how the authentication is managed?
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUsersDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return   super.authenticationManagerBean();
    }
}
