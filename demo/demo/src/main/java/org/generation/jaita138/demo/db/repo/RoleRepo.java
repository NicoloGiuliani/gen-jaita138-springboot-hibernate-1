package org.generation.jaita138.demo.db.repo;

import org.generation.jaita138.demo.db.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
