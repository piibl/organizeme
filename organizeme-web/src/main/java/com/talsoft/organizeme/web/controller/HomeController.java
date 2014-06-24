package com.talsoft.organizeme.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.reference.path.DashboardPath;
import com.talsoft.organizeme.web.reference.path.GlobalPath;

@Controller
@RequestMapping(value = { "/", "/home" })
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	@RequestMapping(method = RequestMethod.GET)
	public String getHomePage(Model model, @AuthenticationPrincipal User user) {
		logger.debug("Authenticated user [" + (user != null ? user.getUsername() : user) + "]");
		if (user != null) {
			// Utilisateur logué, on le renvoie à son tableau de bord
			return "redirect:/" + DashboardPath.USER_DASHBOARD.getPath();
		}
		// Retourne la page d'accueil
		return GlobalPath.HOME.getPath();
	}
}
