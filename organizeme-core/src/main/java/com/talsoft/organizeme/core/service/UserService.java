package com.talsoft.organizeme.core.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.talsoft.organizeme.core.domain.User;
import com.talsoft.organizeme.core.service.repository.UserRepository;

@Named
public class UserService {

	@Inject
	private UserRepository userRepository;

	/**
	 * Crée un utilisateur
	 * 
	 * @param lastName
	 *            : nom de famille
	 * @param firstName
	 *            : prénom
	 * @param login
	 *            : login
	 * @param password
	 *            : mot de passe
	 * @param email
	 *            : email
	 * @return utilisateur
	 */
	public User createUser(String lastName, String firstName, String login, String password, String email) {
		User user = new User(lastName, firstName, login, password, email);
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
