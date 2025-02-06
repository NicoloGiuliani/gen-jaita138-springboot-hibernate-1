package org.generation.jaita138.demo.db.repo;

import org.generation.jaita138.demo.db.entity.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepo extends JpaRepository<SubReddit, Long> {

}
