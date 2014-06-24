package com.talsoft.organizeme.web.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.reference.link.LinkName;
import com.talsoft.organizeme.web.reference.path.DashboardPath;
import com.talsoft.organizeme.web.reference.path.UserPath;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractDashboardController {

	private Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Override
	protected String getMainView() {
		return DashboardPath.USER_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return DashboardController.class;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String getHomePage(Model model, @AuthenticationPrincipal User user) {
		logger.debug("Adding specifics values to model before common process...");
		// Ajout des données spécifiques aux utilisateurs simples
		model.addAttribute(LinkName.TASKS.getName(), linkBuilderFactory.linkTo(getConcreteClass()).slash(UserPath.TASKS.getPath()).withSelfRel());
		model.addAttribute(LinkName.NOTES.getName(), linkBuilderFactory.linkTo(getConcreteClass()).slash(UserPath.NOTES.getPath()).withSelfRel());
		model.addAttribute(LinkName.PLANNER.getName(), null);

		// Appel de la fonction tronc commun, voir controlleur abstrait
		return super.getHomePage(model, user);
	}

}
