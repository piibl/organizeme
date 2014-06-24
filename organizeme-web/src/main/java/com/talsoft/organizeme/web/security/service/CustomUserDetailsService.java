package com.talsoft.organizeme.web.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.service.user.impl.repository.AdministratorRepository;
import com.talsoft.organizeme.core.service.user.impl.repository.SimpleUserRepository;

/**
 * {@link UserDetailsService} customisé, l'information sur les utilisateurs sont issues des repositories
 */
@Named
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Inject
	private SimpleUserRepository simpleUserRepository;
	@Inject
	private AdministratorRepository administratorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Recherche de l'utilisateur dans les repos
		com.talsoft.organizeme.core.domain.user.User user = getUser(username);
		if (user == null) {
			logger.debug("No user registred for [" + username + "]");
			// Aucun utilisateur enregistré pour ce login
			throw new UsernameNotFoundException("User with login " + username + " doesn't exist.");
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
		if (authorities.isEmpty()) {
			// TODO erreur, roles non définis pour ce type d'utilisateurs
		}
		return new User(user.getLogin(), user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

	}

	/**
	 * retourne l'utilisateur correspondant au login passé en paramètre si celui-ci existe
	 * 
	 * @param login
	 * @return
	 */
	private com.talsoft.organizeme.core.domain.user.User getUser(String login) {
		/**
		 * TODO refactor, code immonde
		 */
		// recherche parmi les utilisateurs simples
		com.talsoft.organizeme.core.domain.user.User user = simpleUserRepository.findByLogin(login);
		// si pas de résultat, recherche parmi les admins
		if (user == null) {
			user = administratorRepository.findByLogin(login);
		}
		return user;

	}

	/**
	 * @param user
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(com.talsoft.organizeme.core.domain.user.User user) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(user));
		for (GrantedAuthority grantedAuthority : authList) {
			logger.debug("Authority : [" + grantedAuthority.getAuthority() + "]");
		}
		return authList;
	}

	/**
	 * Retourne les rôles associés à un type d'utilisateur
	 * 
	 * @param user
	 * @return
	 */
	public List<String> getRoles(com.talsoft.organizeme.core.domain.user.User user) {
		List<String> roles = new ArrayList<String>();
		// Si admin
		if (user instanceof Administrator) {
			logger.debug("User [" + user.getLogin() + "] is administrator");
			// Tous les droits sur tout !
			roles.add("ROLE_ADMIN");

		} else if (user instanceof SimpleUser) {
			logger.debug("User [" + user.getLogin() + "] is simple user");
			roles.add("ROLE_USER");
		}
		return roles;
	}

	/**
	 * Encapsule les roles dans des objets {@link SimpleGrantedAuthority}
	 * 
	 * @param roles
	 *            Liste des rôles à encapsuler
	 * @return
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
