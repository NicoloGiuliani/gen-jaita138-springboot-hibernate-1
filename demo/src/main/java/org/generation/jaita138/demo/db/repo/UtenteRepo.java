package org.generation.jaita138.demo.db.repo;

import org.generation.jaita138.demo.db.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long> {

    List<Utente> findByNomeStartingWith(String nome);
    List<Utente> findByCreditoGreaterThan(int credito);
    List<Utente> findByNomeNullOrCognomeNull();
    List<Utente> findByCreditoBetween(int min, int max);
}
