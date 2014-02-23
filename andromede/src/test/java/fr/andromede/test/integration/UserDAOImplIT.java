package fr.andromede.test.integration;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.dao.UserDAO;
import fr.andromede.dto.common.impl.UserDTO;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.Fail.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"UserDAOIT.xml"})
public class UserDAOImplIT {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@After
	public void tearDown() {
		this.userDAO.deleteAll();
	}
	
	@Test
	public void create_user() {
		try {
			UserDTO userDTO = UserDTO.with()
					.login("nletteron")
					.password("password")
					.name("LETTERON")
					.surname("Nicolas")
					.role(UserRoles.USER)
					.create();
			UserDTO createdUser = this.userDAO.create(userDTO);
			assertThat(createdUser).isNotNull().isInstanceOf(UserDTO.class);
			assertThat(createdUser.getLogin()).isEqualTo(userDTO.getLogin());
		} catch(Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void create_user_withNullParam() {
		try {
			UserDTO userDTO = UserDTO.with()
					.login("nletteron")
					.password(null)
					.name("LETTERON")
					.surname("Nicolas")
					.role(null)
					.create();
			UserDTO createdUser = this.userDAO.create(userDTO);
			assertThat(createdUser).isNotNull().isInstanceOf(UserDTO.class);
			assertThat(createdUser.getLogin()).isEqualTo(userDTO.getLogin());
			assertThat(createdUser.getPassword()).isEqualTo(userDTO.getPassword());
		} catch(Exception e) {
			fail(e.getMessage(), e);
		}		
	}
	
	
}
