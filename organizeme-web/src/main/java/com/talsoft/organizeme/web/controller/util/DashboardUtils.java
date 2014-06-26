package com.talsoft.organizeme.web.controller.util;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.talsoft.organizeme.web.controller.dashboard.DashboardController;
import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.reference.link.LinkName;
import com.talsoft.organizeme.web.reference.path.UserPath;

@Named
public class DashboardUtils {

	private Logger logger = LoggerFactory.getLogger(DashboardUtils.class);

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Enrichit le model passé en paramètre avec les liens de menu pour simple utilisateur
	 * 
	 * @param model
	 * @return
	 */
	public Model getDashboardLinks(Model model) {
		logger.debug("Adding user dashboard links to model...");
		model.addAttribute(LinkName.DASHBOARD.getName(), linkBuilderFactory.linkTo(DashboardController.class).withSelfRel());
		model.addAttribute(LinkName.NOTIFICATIONS.getName(), null);
		model.addAttribute(LinkName.NEWS.getName(), null);
		// Ajout des données spécifiques aux utilisateurs simples
		model.addAttribute(LinkName.TASKS.getName(), linkBuilderFactory.linkTo(DashboardController.class).slash(UserPath.TASKS.getPath())
				.withSelfRel());
		model.addAttribute(LinkName.NOTES.getName(), linkBuilderFactory.linkTo(DashboardController.class).slash(UserPath.NOTES.getPath())
				.withSelfRel());
		model.addAttribute(LinkName.PLANNER.getName(), null);
		return model;
	}

}
