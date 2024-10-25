package com.paymybuddy.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.entity.Users;
import com.paymybuddy.repository.UsersRepository;
import com.paymybuddy.service.UsersService;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service
public class UsersServiceImpl implements UsersService {
	
	
	private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(UsersServiceImpl.class);

    public UsersServiceImpl(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
    
    
    
    /**
     * Charge un utilisateur par son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return Les détails de l'utilisateur.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé.
     */
    /*
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Chargement de l'utilisateur avec l'e-mail: {}", email);
        Users user = findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasseword())
                .roles("USER") // Vous pouvez gérer les rôles si nécessaire
                .build();
    }
    */
	 /**
     * Recherche un utilisateur par son adresse e-mail.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @return Un objet Optional contenant l'utilisateur s'il existe, sinon Optional vide.
     */
    Optional<Users> findByEmail(String email){
    	
    	return usersRepository.findByEmail(email);
    	
    	
    }

    /**
     * Recherche un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur.
     * @return Un objet Optional contenant l'utilisateur s'il existe, sinon Optional vide.
     */
    Optional<Users> findById(Long id){
    	
    	
    	return usersRepository.findById(id);
    }

    /**
     * Récupère la liste des tous les utilisateurs.
     *
     * @return La liste des tous les utilisateurs.
     */
    List<Users> findAll(){
    	
    	return usersRepository.findAll();
    }




	@Override
	public Optional<Users> loadByUsername(String username) {
		
		return usersRepository.findByUsername(username);
	}

}
