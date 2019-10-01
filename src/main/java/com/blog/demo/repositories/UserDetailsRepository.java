package com.blog.demo.repositories;



import com.blog.demo.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, Long> {



}
