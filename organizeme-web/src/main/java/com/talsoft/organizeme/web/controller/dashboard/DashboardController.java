package com.talsoft.organizeme.web.controller.dashboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.user.UserResearchService;
import com.talsoft.organizeme.web.link.assembler.NoteResourceAssembler;
import com.talsoft.organizeme.web.link.assembler.TaskResourceAssembler;
import com.talsoft.organizeme.web.reference.path.DashboardPath;
import com.talsoft.organizeme.web.service.NoteManagerService;
import com.talsoft.organizeme.web.service.TaskManagerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractDashboardController {

	// private Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Inject
	private UserResearchService userResearchService;
	@Inject
	private TaskManagerService taskManagerService;
	@Inject
	private NoteManagerService noteManagerService;
	@Inject
	private TaskResourceAssembler taskResourceAssembler;
	@Inject
	private NoteResourceAssembler noteResourceAssembler;

	@Override
	protected String getMainView() {
		return DashboardPath.USER_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return DashboardController.class;
	}

	@Override
	public String getHomePage(Model model, @AuthenticationPrincipal User user) {
		EndUser enduser = userResearchService.findByLogin(user.getUsername());
		// Resources
		List<Resource<Task>> activeTasksResources = taskResourceAssembler.toResource(taskManagerService.findActiveByOwnerLimitResults(enduser, 10));
		List<Resource<Task>> incomingTasksResources = taskResourceAssembler.toResource(taskManagerService
				.findIncomingByOwnerLimitResults(enduser, 10));
		List<Resource<Note>> lastNotesResources = noteResourceAssembler.toResource(noteManagerService.findLastestByOwner(enduser, 10));
		// Ajout au modele
		model.addAttribute("activeTasks", activeTasksResources);
		model.addAttribute("incomingTasks", incomingTasksResources);
		model.addAttribute("lastNotes", lastNotesResources);
		return super.getHomePage(model, user);
	}
}
