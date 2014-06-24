package com.talsoft.organizeme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.reference.path.GlobalPath;

@Controller
public class AccessController {

	@RequestMapping(value = { "/denied" }, method = RequestMethod.GET)
	public String getDeniedPage(Model model) {
		// Retourne la page d'accès refusé
		return GlobalPath.DENIED.getPath();
	}
}
