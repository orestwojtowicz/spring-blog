package com.blog.demo.repositories;



import com.blog.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {


    Optional<Post> findById(Long id);


    @Modifying
    @Query("update Post p set p.postCommentsSize = :commentsSize where p.id = :id")
    void updatePostCommentsSize(@Param("commentsSize") int postCommentsSize, @Param("id") Long id);

    // update existing password, and find user by id
   // @Modifying
  //  @Query("update User u set u.password = :password where u.id = :id")
  //  void updatePassword(@Param("password") String password, @Param("id") Long id);

}
