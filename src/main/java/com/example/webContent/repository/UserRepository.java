package com.example.webContent.repository;

import com.example.webContent.accesDB.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
