package org.generation.jaita138.demo.db.service;

import java.util.List;

import org.generation.jaita138.demo.db.entity.Role;
import org.generation.jaita138.demo.db.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public void save(Role role) {
        roleRepo.save(role);
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Role findById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }

    public void delete(Role role) {
        roleRepo.delete(role);
    }
}
