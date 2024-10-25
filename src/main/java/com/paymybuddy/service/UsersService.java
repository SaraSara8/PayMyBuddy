package com.paymybuddy.service;

import java.util.Optional;

import com.paymybuddy.entity.Users;

public interface UsersService {
	
	
	Optional<Users> loadByUsername(String username);

}
