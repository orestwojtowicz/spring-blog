package com.blog.demo.repositories;


import com.blog.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findByName(String name);


}
