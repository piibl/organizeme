package com.talsoft.organizeme.web.controller.dashboard;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.controller.util.DashboardUtils;
import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;

public abstract class AbstractDashboardController {

	// private Logger logger = LoggerFactory.getLogger(AbstractDashboardController.class);

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;
	@Inject
	private DashboardUtils dashboardUtils;

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
		dashboardUtils.getDashboardLinks(model);
		// Retourne la page d'accueil
		return getMainView();
	}
}
