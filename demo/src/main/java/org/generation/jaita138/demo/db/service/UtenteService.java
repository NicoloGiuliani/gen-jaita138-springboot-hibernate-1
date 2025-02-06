package org.generation.jaita138.demo.db.service;

import java.util.List;

import org.generation.jaita138.demo.db.entity.Utente;
import org.generation.jaita138.demo.db.repo.UtenteRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepo utenteRepo;

    public void save(Utente utente) {
        utenteRepo.save(utente);
    }

    public List<Utente> findAll() {
        return utenteRepo.findAll();
    }

    @Transactional
    public Utente findById(Long id) {

        //return utenteRepo.findById(id).orElse(null);

        Utente utente = utenteRepo.findById(id).orElse(null);
        Hibernate.initialize(utente.getsubReddit());
        return utente;
    }

    public void delete(Utente utente) {
        utenteRepo.delete(utente);
    }

    public List<Utente> findByNomeStartingWith(String nome) {
        return utenteRepo.findByNomeStartingWith(nome);
    }

    public List<Utente> findByCreditoGreaterThan(int credito) {
        return utenteRepo.findByCreditoGreaterThan(credito);
    }

    public List<Utente> findByNomeNullOrCognomeNull(){
        return utenteRepo.findByNomeNullOrCognomeNull();
    }
    
    public List<Utente> findByCreditoBetween(int min, int max) {
        return utenteRepo.findByCreditoBetween(min, max);
    }
}
