package fr.andromede.test.integration;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.common.exceptions.BusinessServiceException;
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
	public void create_user() {
		try {
			User user = User.with()
					.login("nletteron")
					.password("password")
					.name("LETTERON")
					.surname("Nicolas")
					.role(UserRoles.USER)
					.create();
			User createdUser = userBS.createUser(user);
			assertThat(createdUser).isNotNull();
			assertThat(createdUser.getLogin()).isEqualTo(user.getLogin());
			assertThat(createdUser.getPassword()).isEqualTo(user.getPassword());
			assertThat(createdUser.getName()).isEqualTo(user.getName());
			assertThat(createdUser.getSurname()).isEqualTo(user.getSurname());
			assertThat(createdUser.getRole()).isEqualTo(user.getRole());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void create_user_withNullParam() {
		try {
			User user = User.with()
					.login("nletteron")
					.password("password")
					.name(null)
					.surname("Nicolas")
					.role(null)
					.create();
			User createdUser = userBS.createUser(user);
			assertThat(createdUser).isNotNull();
			assertThat(createdUser.getLogin()).isEqualTo(user.getLogin());
			assertThat(createdUser.getPassword()).isEqualTo(user.getPassword());
			assertThat(createdUser.getName()).isEqualTo(user.getName());
			assertThat(createdUser.getSurname()).isEqualTo(user.getSurname());
			assertThat(createdUser.getRole()).isEqualTo(user.getRole());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void create_user_withNullLogin_Exception() {
		try {
			User user = User.with()
					.login(null)
					.password("password")
					.name(null)
					.surname("Nicolas")
					.role(null)
					.create();
			userBS.createUser(user);
			fail("L'utilisateur avec un login null n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(BusinessServiceException.class);
		}
	}
	
	@Test
	public void create_user_withEmptyLogin_Exception() {
		try {
			User user = User.with()
					.login("")
					.password("password")
					.name(null)
					.surname("Nicolas")
					.role(null)
					.create();
			userBS.createUser(user);
			fail("L'utilisateur avec un login vide n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(BusinessServiceException.class);
		}
	}
	
	@Test
	public void create_user_withNullPassword_Exception() {
		try {
			User user = User.with()
					.login("nletteron")
					.password(null)
					.name(null)
					.surname("Nicolas")
					.role(null)
					.create();
			userBS.createUser(user);
			fail("L'utilisateur avec un password null n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(BusinessServiceException.class);
		}
	}
		
	@Test
	public void create_user_withEmptyPassword_Exception() {
		try {
			User user = User.with()
					.login("nletteron")
					.password("")
					.name(null)
					.surname("Nicolas")
					.role(null)
					.create();
			userBS.createUser(user);
			fail("L'utilisateur avec un password vide n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(BusinessServiceException.class);
		}
	}	
	
}
