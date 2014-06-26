package com.talsoft.organizeme.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talsoft.organizeme.web.reference.path.DashboardPath;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractDashboardController {

	// private Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Override
	protected String getMainView() {
		return DashboardPath.USER_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return DashboardController.class;
	}

}
