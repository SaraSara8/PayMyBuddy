package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.entity.Transaction;
import com.paymybuddy.entity.Users;

public interface TransactionService {

	
	
	/**
     * Récupère la liste des transactions reçues par un utilisateur donné.
     *
     * @param receiver L'utilisateur qui a reçu les transactions.
     * @return La liste des transactions reçues par l'utilisateur.
     */
    List<Transaction> findByReceiver(Users receiver);
	
	
	/**
     * Récupère la liste des transactions effectuées par un utilisateur donné.
     *
     * @param sender L'utilisateur qui a envoyé les transactions.
     * @return La liste des transactions envoyées par l'utilisateur.
     */
    List<Transaction> findBySender(Users sender);
}
