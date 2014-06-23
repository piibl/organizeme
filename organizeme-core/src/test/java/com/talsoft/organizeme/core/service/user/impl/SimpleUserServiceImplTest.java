/**
 * 
 */
package com.talsoft.organizeme.core.service.user.impl;

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
public class SimpleUserServiceImplTest {

	// Pas d'implémentation, à voir si valeur ajouté.
	// On ne teste pas les fonctionnalités Spring DATA

}
