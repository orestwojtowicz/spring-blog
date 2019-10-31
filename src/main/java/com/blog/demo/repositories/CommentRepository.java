package com.blog.demo.repositories;


import com.blog.demo.entities.Comment;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {




}
