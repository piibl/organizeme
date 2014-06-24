package com.talsoft.organizeme.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.domain.user.User;
import com.talsoft.organizeme.core.service.user.SimpleUserService;
import com.talsoft.organizeme.web.reference.GlobalPath;

@Controller
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	private static final String registrationPath = "fragment/registration :: registration-form";

	@Inject
	private SimpleUserService simpleUserService;

	@ModelAttribute("userStructure")
	public User getUserStructure() {
		return new User();
	}

	@RequestMapping(value = { "/registration" })
	public String getRegistrationPage(Model model) {
		// Retourne le formulaire d'inscription
		return registrationPath;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registerStudent(SimpleUser user, BindingResult result, Model model) {
		logger.debug("POST Request on registration form");
		if (result.hasErrors()) {
			// Cas impossible, pas de validation pour l'instant ;)
		}
		/***
		 * TODO IMPLEMENTATION SUCCESS !!
		 */
		model.addAttribute("loginNewUser", simpleUserService.save(user).getLogin());
		// Retourne la page d'accueil
		return GlobalPath.HOME.getPath();
	}

}
