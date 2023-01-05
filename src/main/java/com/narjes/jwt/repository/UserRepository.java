package com.narjes.jwt.repository;

import com.narjes.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String username);

}
