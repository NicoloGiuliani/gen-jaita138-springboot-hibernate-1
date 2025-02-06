package org.generation.jaita138.demo.CliManager;

import java.util.List;
import java.util.Scanner;

import org.generation.jaita138.demo.db.entity.Utente;
import org.generation.jaita138.demo.db.entity.Role;
import org.generation.jaita138.demo.db.entity.SubReddit;
import org.generation.jaita138.demo.db.service.RoleService;
import org.generation.jaita138.demo.db.service.SubRedditService;
import org.generation.jaita138.demo.db.service.UtenteService;

public class CliManager {

    private Scanner sc;
    
    private UtenteService utenteService;
    private RoleService roleService;
    private SubRedditService subRedditService;

    // costruttore
    public CliManager(UtenteService utenteService, RoleService roleService, SubRedditService subRedditService) {

        sc = new Scanner(System.in);
        this.utenteService = utenteService;
        this.roleService = roleService;
        this.subRedditService = subRedditService;

        printOptions();
    }

    private void printOptions() {

        System.out.println("Operazioni:");
        System.out.println("1. Stampa tutti gli utenti");
        System.out.println("2. Inserisci nuovo utente");
        System.out.println("3. Modifica utente");
        System.out.println("4. Elimina utente");
        System.out.println("5. Trova utenti che iniziano con per a");
        System.out.println("6. Trovare gli utenti con credito maggiore di 1000");
        System.out.println("7. Trovare gli utenti con nome o cognome null");
        System.out.println("8. trovare gli utenti con credito positivo ma inferiore a 1000");
        System.out.println("9. Stampa dettagli utente");
        System.out.println("0. Esci");
        printSeparetor();

        System.out.println("Inserisci il numero dell'operazione desiderata:");

        String strValue = sc.nextLine();
        int value = Integer.parseInt(strValue);
        printSeparetor();
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
                printUserDetails();
                break;
            case 0:
                return;
            default:
                System.out.println("Operazione non valida");
                printSeparetor();
                break;
        }

        printOptions();
    }

    private void readAll() {

        List<Utente> utenti = utenteService.findAll();
        System.out.println("utenti:");
        System.out.println(utenti);
        printSeparetor();
    }

    private void insert() {
        Utente u = new Utente();
        save(u);
    }

    private void edit() {
        System.out.println("edit id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u == null) {
            System.out.println("utente non trovato");
            printSeparetor();
            return;
        }
        save(u);
    }

    private void save(Utente u) {

        boolean isEdit = (u.getId() == null);

        System.out.println("nome:" + (isEdit
                ? ""
                : "(" + u.getNome() + ")"));
        String nome = sc.nextLine();
        if (!nome.isEmpty())
            u.setNome(nome);

        System.out.println("cognome:" + (isEdit
                ? ""
                : "(" + u.getCognome() + ")"));
        String cognome = sc.nextLine();
        if (!cognome.isEmpty())
            u.setCognome(cognome);

        System.out.println("username:" + (isEdit
                ? ""
                : "(" + u.getUsername() + ")"));
        String username = sc.nextLine();
        if (!username.isEmpty())
            u.setUsername(username);

        System.out.println("password:" + (isEdit
                ? ""
                : "(" + u.getPassword() + ")"));
        String password = sc.nextLine();
        if (!password.isEmpty())
            u.setPassword(password);

        System.out.println("credito:" + (isEdit
                ? ""
                : "(" + u.getCredito() + ")"));
        String strCredito = sc.nextLine();
        if (!strCredito.isEmpty()) {
            int credito = Integer.parseInt(strCredito);
            u.setCredito(credito);
        }

        // BLOCCO RELAZIONE 1aN
        List<Role> roles = roleService.findAll();
        if (!roles.isEmpty()) {
            System.out.println("ruoli:");
            roles.stream()
                    .map(r -> r.getId() + ". " + r.getNome() + " - " + r.getDescrizione())
                    .forEach(System.out::println);
            String roleIdStr = (u.getRole() == null)
                    ? "nessun ruolo assegnato"
                    : "" + u.getRole().getId();
            System.out.println("ruolo id" + (isEdit
                    ? " (" + roleIdStr + ")"
                    : ""));
            String strRoleId = sc.nextLine();
            if (!strRoleId.isEmpty()) {
                Long roleId = Long.parseLong(strRoleId);
                Role role = roleService.findById(roleId);
                u.setRole(role);
                System.out.println(role);
            }
        } else {
            System.out.println("Nessun ruolo disponibile. Riprovare in futuro!");
        }
        utenteService.save(u);

        // BLOCCO RELAZIONE NaM (ManyToMany)
        String hasSubReddit = "y";
        List<SubReddit> subReddits = subRedditService.findAll();
        while (hasSubReddit.equals("y")) {

            System.out.println("has SubReddit? (y/n)");
            hasSubReddit = sc.nextLine();

            if (!hasSubReddit.equals("y")) {

                utenteService.save(u);
                printSeparetor();
                return;
            }

            System.out.println("SubReddit:");
            subReddits.stream()
                    .filter(r -> !u.getsubReddit().contains(r))
                    .map(r -> r.getId() + ". " + r.getNome() + " - " + r.getDescrizione())
                    .forEach(System.out::println);

            System.out.println("SubReddit id");
            String stSubRedditId = sc.nextLine();
            Long subRedditId = Long.parseLong(stSubRedditId);
            SubReddit subReddit = subRedditService.findById(subRedditId);
            /*
            if (subReddit == null) {
                System.out.println("SubReddit non trovato");
                printSeparetor();
                continue;
            }
            if (u.getsubReddit().contains(subReddit)) {
                System.out.println("SubReddit già assegnato");
                printSeparetor();
                continue;
            }
            */
            u.addSubReddit(subReddit);
        }
    }

    private void printUserDetails() {

        System.out.println("utente id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u == null) {
            System.out.println("Utente non trovato");
            printSeparetor();
            return;
        }

        System.out.println("Utente:");
        System.out.println(u);
        System.out.println("SubReddit:");
        System.out.println(u.getsubReddit());
        printSeparetor();

    }

    private void printSeparetor() {
        System.out.println("------------------------------------------------");
    }

    private void delete() {

        System.out.println("delete id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u != null) {
            utenteService.delete(u);
            System.out.println("Utente " + strId + " eliminato");
            printSeparetor();
        } else {
            System.out.println("Utente non trovato");
            printSeparetor();
        }
    }

    private void findByNomeStartingWith() {
        System.out.println("Utenti che iniziano con 'a':");
        List<Utente> u = utenteService.findByNomeStartingWith("a");
        System.out.println(u);
        printSeparetor();
    }

    private void findByCreditoGreaterThan() {
        System.out.println("Utenti con credito maggiore di 1000:");
        List<Utente> u = utenteService.findByCreditoGreaterThan(10*100);
        System.out.println(u);
        printSeparetor();
    }

    private void findByNomeNullOrCognomeNull() {
        System.out.println("Utenti con nome o cognome null:");
        List<Utente> u = utenteService.findByNomeNullOrCognomeNull();
        System.out.println(u);
        printSeparetor();
    }

    private void findByCreditoBetween() {
        System.out.println("Utenti con credito positivo ma inferiore a 1000:");
        List<Utente> u = utenteService.findByCreditoBetween(0, 10 * 100);
        System.out.println(u);
        printSeparetor();
    }
}
