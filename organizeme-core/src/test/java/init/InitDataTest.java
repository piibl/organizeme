package init;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.service.task.TaskService;
import com.talsoft.organizeme.core.service.user.AdministratorService;
import com.talsoft.organizeme.core.service.user.SimpleUserService;

import config.CoreTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_INIT_DATA })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class InitDataTest {

	@Inject
	private AdministratorService administratorService;

	@Inject
	private SimpleUserService simpleUserService;

	@Inject
	private TaskService taskService;

	@Test
	@Rollback(false)
	public void initAdmin() {
		// Insertion en base d'un administrateur
		administratorService.save(new Administrator("admin", "admin", "admin", "admin", "admin.admin@admin.admin"));
	}

	@Test
	@Rollback(false)
	public void initUser() {
		// Insertion en base d'un utilisateur
		EndUser user = simpleUserService.save(new SimpleUser("hector", "hector", "hector", "hector", "hector.hector@hector.hector"));
		// Sauvegarde de quelques taches
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test1", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test2", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));
		taskService.save(new Task("test3", "test", "high", "Paris", user, new DateTime(2014, 7, 4, 10, 0), new DateTime(2014, 7, 4, 11, 0)));

	}

}
