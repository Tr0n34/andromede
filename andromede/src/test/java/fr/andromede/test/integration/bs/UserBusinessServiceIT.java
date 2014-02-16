package fr.andromede.test.integration.bs;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.entity.common.impl.User;
import fr.andromede.services.UserBusinessService;
import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.Fail.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"USerBusinessServiceIT.xml"})
public class UserBusinessServiceIT {

	@Resource(name="userBS")
	private UserBusinessService userBS;
	
	@Test
	public void create() {
		try {
			User user = User.with()
					.login("nletteron")
					.password("password")
					.name("LETTERON")
					.surname("Nicolas")
					.role(UserRoles.USER)
					.create();
			User createdUser = userBS.createUser(user);
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
}
