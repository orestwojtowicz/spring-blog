package com.blog.demo.repositories;



import com.blog.demo.entities.Post;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {


    Optional<Post> findById(Long id);

    @Modifying
    @Query("update Post p set p.postCommentsSize = :commentsSize where p.id = :id")
    void updatePostCommentsSize(@Param("commentsSize") int postCommentsSize, @Param("id") Long id);


    // find all post with releated topic

    List<Post> findAllByPostTopics(String postTopic);

    // and sort them date
    List<Post> findDistinctByPostTopics(String postTopics);


}
