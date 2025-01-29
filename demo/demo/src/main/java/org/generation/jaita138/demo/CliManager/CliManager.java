package org.generation.jaita138.demo.CliManager;

import java.util.List;
import java.util.Scanner;

import org.generation.jaita138.demo.db.entity.Utente;
import org.generation.jaita138.demo.db.entity.Role;
import org.generation.jaita138.demo.db.service.RoleService;
import org.generation.jaita138.demo.db.service.UtenteService;

public class CliManager {

    private Scanner sc;
    private UtenteService utenteService;
    private RoleService roleService;

    // costruttore
    public CliManager(UtenteService utenteService, RoleService roleService) {

        sc = new Scanner(System.in);
        this.utenteService = utenteService;
        this.roleService = roleService;

        printOptions();
    }

    private void printOptions() {

        System.out.println("Operazioni:");
        System.out.println("1. Stampa tutti gli utenti");
        System.out.println("2. Inserisci nuovo utente");
        System.out.println("3. Modifica utente");
        System.out.println("4. Elimina utente");
        System.out.println("5. Trova utenti che iniziano con per a");
        System.out.println("6. Trovare gli utenti con credito maggiore di 10");
        System.out.println("7. Trovare gli utenti con nome o cognome null");
        System.out.println("8. trovare gli utenti con credito positivo ma inferiore a 10");
        System.out.println("9. Esci");
        System.out.println("");

        String strValue = sc.nextLine();
        int value = Integer.parseInt(strValue);

        switch (value) {

            case 1:
                readAll();
                break;
            case 2:
                insert();
                break;
            case 3:
                edit();
                break;
            case 4:
                delete();
                break;
            case 5:
                findByNomeStartingWith();
                break;
            case 6:
                findByCreditoGreaterThan();
                break;
            case 7:
                findByNomeNullOrCognomeNull();
                break;
            case 8:
                findByCreditoBetween();
                break;
            case 9:
                return;
            default:
                System.out.println("Operazione non valida");
                System.out.println("-------------------------------------");
                break;
        }

        printOptions();
    }

    private void readAll() {

        List<Utente> utenti = utenteService.findAll();
        System.out.println("utenti:");
        System.out.println(utenti);
        System.out.println("-------------------------------------");
    }

    private void insert() {

        Utente u = new Utente();

        System.out.println("nome:");
        String nome = sc.nextLine();
        u.setNome(nome);

        System.out.println("cognome:");
        String cognome = sc.nextLine();
        u.setCognome(cognome);

        System.out.println("username:");
        String username = sc.nextLine();
        u.setUsername(username);

        System.out.println("password:");
        String password = sc.nextLine();
        u.setPassword(password);

        System.out.println("credito:");
        String strCredito = sc.nextLine();
        int credito = Integer.parseInt(strCredito);
        u.setCredito(credito);

        System.out.println("ruoli:");
        List<Role> roles = roleService.findAll();
        System.out.println(roles);
        System.out.println("ruolo id:");
        String strRoleId = sc.nextLine();
        Long roleId = Long.parseLong(strRoleId);
        Role role = roleService.findById(roleId);
        System.out.println(role);

        utenteService.save(u);
    }

    private void edit() {

        System.out.println("edit id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u == null) {
            System.out.println("Utente non trovato");
            System.out.println("-------------------------------------");
            return;
        }

        System.out.println("nome: (" + u.getNome() + ")");
        String nome = sc.nextLine();
        u.setNome(nome);

        System.out.println("cognome: (" + u.getCognome() + ")");
        String cognome = sc.nextLine();
        u.setCognome(cognome);

        System.out.println("username: (" + u.getUsername() + ")");
        String username = sc.nextLine();
        u.setUsername(username);

        System.out.println("password: (" + u.getPassword() + ")");
        String password = sc.nextLine();
        u.setPassword(password);

        System.out.println("credito: (" + u.getCredito() + ")");
        String strCredito = sc.nextLine();
        int credito = Integer.parseInt(strCredito);
        u.setCredito(credito);

        utenteService.save(u);
    }

    private void delete() {

        System.out.println("delete id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u != null) {
            utenteService.delete(u);
            System.out.println("Utente " + strId + " eliminato");
            System.out.println("-------------------------------------");
        } else {
            System.out.println("Utente non trovato");
            System.out.println("-------------------------------------");
        }
    }

    private void findByNomeStartingWith() {
        List<Utente> u = utenteService.findByNomeStartingWith("a");
        System.out.println(u);
    }

    private void findByCreditoGreaterThan() {
        List<Utente> u = utenteService.findByCreditoGreaterThan(10);
        System.out.println(u);
    }

    private void findByNomeNullOrCognomeNull() {
        List<Utente> u = utenteService.findByNomeNullOrCognomeNull();
        System.out.println(u);
    }

    private void findByCreditoBetween() {
        List<Utente> u = utenteService.findByCreditoBetween(0, 10 * 100);
        System.out.println(u);
    }
}
