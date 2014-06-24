package com.talsoft.organizeme.web.security.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.talsoft.organizeme.web.reference.DashboardPath;
import com.talsoft.organizeme.web.reference.GlobalPath;

/**
 * Service de redirection après succes d'authentification
 */
@Named
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	protected Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		// Réponse déjà renvoyée, le filtre ne peut traiter la redirection
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		// Redirection de l'utilisateur selon son rôle
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Détermination des URL cibles
	 * 
	 * @param authentication
	 *            : authentification à traiter
	 * @return
	 */
	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}
		/**
		 * TODO externaliser les paths
		 */
		if (isUser) {
			// Base
			return "/" + DashboardPath.USER_DASHBOARD.getPath();
			// return "/" + GlobalPath.HOME.getPath();
		} else if (isAdmin) {
			// Tableau de bord administrateur, àvoir utilité
			// return "/" + DashboardPath.ADMIN_DASHBOARD.getPath();
			return "/" + GlobalPath.HOME.getPath();
		} else {
			// TODO erreur détaillée
			throw new IllegalStateException();
		}
	}

	/**
	 * Nettoie la session après succès
	 * 
	 * @param request
	 *            : requête d'authentification
	 */
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}