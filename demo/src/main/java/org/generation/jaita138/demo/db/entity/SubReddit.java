package org.generation.jaita138.demo.db.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class SubReddit extends Object{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @ManyToMany(mappedBy = "subReddits")
    private List<Utente> utenti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    @Override
    public String toString() {
        return "SubReddit \n"
                + "[id=" + id + ",\n"
                + "nome=" + nome + ",\n"
                + "descrizione=" + descrizione + ",\n" + "]";
    }
    
    //hashcode ritorna un valore intero che deve identificare univocamente l'oggetto
    //ritorna il valore intero dell'id
    //se due oggetti sono uguali allora il loro hashcode deve essere uguale
    @Override
    public int hashCode() {
       
        return getId().intValue();
    }
    //prende in input un oggetto e lo confronta con l'istanza
    //ritorna true se l'hashcode(id) è uguale tra istanza e oggetto
    @Override
    public boolean equals(Object obj) {
        
        return hashCode() == obj.hashCode();
    }
}
