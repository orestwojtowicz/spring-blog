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



   @Transactional
   @Modifying
   @Query(value = "UPDATE User u set u.password = :password WHERE u.email = :email", nativeQuery = true)
   default void resetUserPassword(@Param("password") String email) {

   }

   @Modifying
   @Query("update User u set u.password = :password where u.email = :email")
   void setUserById(@Param("password") String password);



   @Modifying
   @Query("update User u set u.nick = :nick where u.email = :email")
   void setNick(@Param("nick") String nick);


  /* @Modifying
   @Query("update UserEncryptionData u set u.salt = :salt where u.id = :id")
   int setUserById(@Param("salt") String salt, @Param("id") Long id);*/




}
