package com.paymybuddy.controller;

import com.paymybuddy.entity.Transaction;
import com.paymybuddy.entity.User;
import com.paymybuddy.service.UserService;
import com.paymybuddy.service.TransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.Data;

import java.security.Principal;
import java.util.List;


/**
 * Contrôleur pour gérer les transactions liées à l'utilisateur
 * lister les differentes transactions et effectuer une transaction
 */
@Data
@Controller
public class TransactionController {

    private final UserService userService;
    private final TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    public TransactionController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    /**
     * Gère l'envoi d'argent à une connexion.
     *
     * @param principal L'utilisateur actuellement connecté.
     * @param model          Le modèle pour la vue.
     * @return Redirige vers la page des transfers.
     */
    @GetMapping("/transfer")
    public String showTransferPage(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        if (user != null) {
            List<Transaction> transactions = transactionService.findTransactionsForUser(user);
            model.addAttribute("transactions", transactions);
        }
        return "transfer"; // Correspond au fichier transfer.html
    }


    /**
     * Gère l'envoi d'argent à une connexion.
     *
     * @param principal L'utilisateur actuellement connecté.
     * @param connectionEmail L'e-mail de la connexion à laquelle envoyer de l'argent.
     * @param amount         Le montant à envoyer.
     * @param description    La description de la transaction.
     * @param model          Le modèle pour la vue.
     * @return Redirige vers la page d'accueil.
     */
    @PostMapping("/transactions/send")
    public String sendMoney(Principal principal,
                            @RequestParam("connectionEmail") String connectionEmail,
                            @RequestParam("amount") Double amount,
                            @RequestParam("description") String description,
                            Model model) {

        logger.info("Envoi d'argent de {} à {}", principal.getName(), connectionEmail);

        User sender = userService.findByEmail(principal.getName()).orElse(null);
        User receiver = userService.findByEmail(connectionEmail).orElse(null);

        if (sender != null && receiver != null && sender.getConnections().contains(receiver)) {
            try {
                transactionService.sendMoney(sender, receiver, amount, description);
                logger.info("Transaction réussie de {} à {}", sender.getEmail(), receiver.getEmail());
                model.addAttribute("success", "Relation ajoutée avec succès.");
            } catch (Exception e) {
                logger.error("Erreur lors de la transaction: {}", e.getMessage());
                model.addAttribute("error","Erreur lors de la transaction");
            }
        } else {
            logger.warn("Transaction non autorisée entre {} et {}", principal.getName(), connectionEmail);
            model.addAttribute("error", "Transaction non autorisée.");

        }

        List<Transaction> transactions = transactionService.findTransactionsForUser(sender);
        model.addAttribute("user", sender);
        model.addAttribute("transactions", transactions);
        return "transfer";
    }

}