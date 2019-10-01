package com.blog.demo.services;



import com.blog.demo.entities.Role;
import com.blog.demo.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
