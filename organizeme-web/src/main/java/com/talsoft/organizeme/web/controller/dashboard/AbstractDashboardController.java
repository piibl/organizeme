package com.talsoft.organizeme.web.controller.dashboard;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.reference.LinkName;

public abstract class AbstractDashboardController {

	private Logger logger = LoggerFactory.getLogger(AbstractDashboardController.class);

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Retourne la vue correspondant au tableau de bord
	 */
	protected abstract String getMainView();

	/**
	 * Retourne la classe concrète, permettant de construire des liens vers celle-ci
	 */
	protected abstract Class<?> getConcreteClass();

	@RequestMapping(method = RequestMethod.GET)
	public String getHomePage(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("login", user.getUsername());
		model.addAttribute(LinkName.DASHBOARD.getName(), linkBuilderFactory.linkTo(getConcreteClass()).withSelfRel());
		model.addAttribute(LinkName.NOTIFICATIONS.getName(), null);
		model.addAttribute(LinkName.NEWS.getName(), null);
		for (String name : model.asMap().keySet()) {
			logger.debug("model attribute [" + name + "][" + model.asMap().get(name) + "]");
		}
		// Retourne la page d'accueil
		return getMainView();
	}
}
