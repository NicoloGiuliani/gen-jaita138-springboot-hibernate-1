package org.generation.jaita138.demo.db.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String nome, cognome, password;

    @Column(length = 128)
    private String username;

    @Column(columnDefinition = "INT")
    private int credito;

    @ManyToOne
    private Role role;

    @ManyToMany
    private List<SubReddit> subReddits;

    public Utente() {
        this.subReddits = new ArrayList<>();
    }

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<SubReddit> getsubReddit() {
        return this.subReddits;
    }

    public void setsubReddits(List<SubReddit> subReddits) {
        this.subReddits = subReddits;
    }

    public void addSubReddit(SubReddit SubReddit) {

        if (this.subReddits == null)
            this.subReddits = new ArrayList<>();

        this.subReddits.add(SubReddit);
    }

    public void clearSubReddits() {
        if (this.subReddits != null) {
            this.subReddits = null;
        }
    }

    @Override
    public String toString() {
        return "Utente: \n"
                + "[id= " + id + ",\n "
                + "nome= " + nome + ",\n "
                + "cognome= " + cognome + ",\n "
                + "password= " + password + ",\n "
                + "username= " + username + ",\n "
                + "credito= " + credito + ",\n "
                + "ruolo= " + role + "]";
    }
}
