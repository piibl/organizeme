/**
 * 
 */
package com.talsoft.organizeme.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author A547891
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_spring-config-hsql.xml")
@Transactional
public class UserServiceTest {

	@Inject
	private UserService userService;

	@Test
	public void testCreateUser() {
		assertNotNull(userService.createUser("Popol", "franck", "123", "123", "popol@123.com"));
	}

	@Test
	public void testFindAll() {
		for (int i = 0; i < 2; i++) {
			userService.createUser("Popol" + i, "franck", "123", "123", "popol@123.com");
		}
		assertEquals(2, userService.findAll().size());

	}

}
