package org.generation.jaita138.demo;

//import org.generation.jaita138.demo.db.entity.Utente;
import org.generation.jaita138.demo.CliManager.CliManager;
import org.generation.jaita138.demo.db.service.RoleService;
import org.generation.jaita138.demo.db.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new CliManager(utenteService, roleService);
		//test1();
	}

	/*public void test1() {
		Utente utente = new Utente();
		utente.setNome("");
		utente.setCognome("");
		utente.setUsername("");
		utente.setPassword("");
		utente.setCredito(0);

		System.out.println("PRINT USER BEFORE SAVE");
		System.out.println(utente);
		System.out.println("--------------------------------");

		// STORE NEW utente
		utenteService.save(utente);

		System.out.println("PRINT USER AFTER SAVE");
		System.out.println(utente);
		System.out.println("--------------------------------");
	}
	*/
}

