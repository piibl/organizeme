package init;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.core.domain.user.SimpleUser;
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
		simpleUserService.save(new SimpleUser("hector", "hector", "hector", "hector", "hector.hector@hector.hector"));

	}

}
