package com.blog.demo.repositories;


import com.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


   Optional<User> findByEmail(String email);
   Optional<User> findByEmailAndActivationCode(String email, String activationCode);
   Optional<User> findByEmailAndResetPasswordToken(String email, String passwordToken);

   // update existing password, and find user by id
   @Modifying
   @Query("update User u set u.password = :password where u.id = :id")
   void updatePassword(@Param("password") String password, @Param("id") Long id);






}
