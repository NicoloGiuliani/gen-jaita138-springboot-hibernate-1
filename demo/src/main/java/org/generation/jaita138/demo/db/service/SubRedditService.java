package org.generation.jaita138.demo.db.service;

import java.util.List;

import org.generation.jaita138.demo.db.entity.SubReddit;
import org.generation.jaita138.demo.db.repo.SubRedditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRedditService {

    @Autowired
    private SubRedditRepo subRedditRepo;

    public void save(SubReddit subReddit) {
        subRedditRepo.save(subReddit);
    }

    public List<SubReddit> findAll() {
        return subRedditRepo.findAll();
    }

    public SubReddit findById(Long id) {
        return subRedditRepo.findById(id).orElse(null);
    }

    public void delete(SubReddit subReddit) {
        subRedditRepo.delete(subReddit);
    }
}