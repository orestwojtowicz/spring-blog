package com.blog.demo.repositories;


import com.blog.demo.entities.Comment;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


   Optional<User> findByEmail(String email);
   Optional<User> findByEmailAndActivationCode(String email, String activationCode);

  // User findByEmail(String email);



}
