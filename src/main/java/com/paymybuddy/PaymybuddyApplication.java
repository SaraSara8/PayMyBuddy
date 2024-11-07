package com.paymybuddy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Classe principale de l'application PayMyBuddy.
 * Cette classe initialise et lance l'application Spring Boot.
 */
@SpringBootApplication
public class PaymybuddyApplication {

	// Création d'un logger pour suivre les événements de l'application
	private static final Logger logger = LoggerFactory.getLogger(PaymybuddyApplication.class);

	/**
	 * Point d'entrée principal de l'application.
	 *
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		logger.info("Démarrage de l'application PayMyBuddy...");
		SpringApplication.run(PaymybuddyApplication.class, args);
		logger.info("Application PayMyBuddy démarrée avec succès.");
	}
}