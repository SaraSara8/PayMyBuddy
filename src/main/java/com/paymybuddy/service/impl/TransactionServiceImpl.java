package com.paymybuddy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymybuddy.entity.Transaction;
import com.paymybuddy.entity.Users;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.service.TransactionService;

import lombok.Data;

@Data
@Service
public class TransactionServiceImpl implements TransactionService {
	
	
	private final TransactionRepository transactionRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		
		this.transactionRepository = transactionRepository;
	}
	

	@Override
	public List<Transaction> findBySender(Users sender) {
		
		return transactionRepository.findBySender(sender.getId());
	}



	@Override
	public List<Transaction> findByReceiver(Users receiver) {
	
		return transactionRepository.findByReceiver(receiver.getId());
	}
	
	
	

}
