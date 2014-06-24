/**
 * 
 */
package com.talsoft.organizeme.web.security.authentication;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.talsoft.organizeme.web.reference.DashboardPath;

public class CustomAuthenticationSuccessHandlerTest {

	private List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	private CustomAuthenticationSuccessHandler handler;

	@Before
	public void setUp() {
		handler = new CustomAuthenticationSuccessHandler();
		authorities = new ArrayList<SimpleGrantedAuthority>();
	}

	@Test
	public void testDetermineTargetUrlForSimpleUser() {
		// Cas 1 : l'utilisateur est un utilisateur simple
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		// Perform et assert
		Assert.assertEquals("/" + DashboardPath.USER_DASHBOARD.getPath(),
				handler.determineTargetUrl(new AnonymousAuthenticationToken("user1", new Object(), authorities)));
	}

	@Test
	public void testDetermineTargetUrlForAdmin() {
		// Cas 2 : l'utilisateur est un administrateur
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		// Perform et assert
		Assert.assertEquals("/" + DashboardPath.ADMIN_DASHBOARD.getPath(),
				handler.determineTargetUrl(new AnonymousAuthenticationToken("user1", new Object(), authorities)));
	}

	@Test(expected = IllegalStateException.class)
	public void testDetermineTargetUrlKo() {
		// Cas 3 : l'utilisateur a un role non implémenté
		authorities.add(new SimpleGrantedAuthority("ROLE_LAMBDA"));
		// Perform
		handler.determineTargetUrl(new AnonymousAuthenticationToken("user1", new Object(), authorities));
	}
}
