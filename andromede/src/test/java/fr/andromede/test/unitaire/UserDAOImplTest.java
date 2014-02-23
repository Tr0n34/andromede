package fr.andromede.test.unitaire;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.util.ReflectionTestUtils;

import com.mongodb.MongoException;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.dao.SequencerBuilder;
import fr.andromede.dao.UserDAO;
import fr.andromede.dao.impl.UserDAOImpl;
import fr.andromede.dto.common.impl.UserDTO;
import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.Fail.*;
import static org.mockito.Mockito.*;

public class UserDAOImplTest {
	
	private static final String ID_SEQUENCER_TEST = "1";
	
	private UserDAO userDAO;
	private MongoOperations template;
	private SequencerBuilder sequencer;
	
	@Before
	public void setUp() {
		this.userDAO = new UserDAOImpl();
		this.template = mock(MongoOperations.class);
		this.sequencer = mock(SequencerBuilder.class);
		ReflectionTestUtils.setField(this.userDAO, "template", template);
		ReflectionTestUtils.setField(this.userDAO, "sequencer", sequencer);
		when(sequencer.getNextID()).thenReturn(ID_SEQUENCER_TEST);
	}
	
	@Test
	public void mongo_error() {
		UserDTO userDTO = UserDTO.with()
				.login("nletteron")
				.password("TEST")
				.name("LETTERON")
				.surname("Nicolas")
				.role(UserRoles.USER)
				.create();
		doThrow(MongoException.class).when(this.template).insert(userDTO);
		try {
			this.userDAO.create(userDTO);
		} catch(Exception e) {
			assertThat(e).isInstanceOf(MongoException.class);
		}
	}
	
	@Test
	public void create_user() {
		String expectedId = this.sequencer.getNextID();
		UserDTO userDTO = UserDTO.with()
				.login("nletteron")
				.password("TEST")
				.name("LETTERON")
				.surname("Nicolas")
				.role(UserRoles.USER)
				.create();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(this.template).insert(userDTO);
		this.userDAO.create(userDTO);
		verify(this.template, times(1)).insert(userDTO);
		assertThat(userDTO).isNotNull();
		assertThat(userDTO.getId()).isEqualTo(expectedId);
	}
	
	@Test
	public void create_user_NullUser() {
		try {
			this.userDAO.create(null);
			fail("L'utilisateur [null] n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}
	}
	
	@Test
	public void create_user_ExistingUser() {
		try {
			this.userDAO.create(UserDTO.with().login("test").id(ID_SEQUENCER_TEST).create());
			fail("L'utilisateur est déjà existant et n'aurait pas du être créé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}
	}

	@Test
	public void delete_by_key() {
		String idToDelete = ID_SEQUENCER_TEST;
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}			
		}).when(this.template).remove(Query.query(Criteria.where("id").is(idToDelete)), UserDTO.class);
		try {
			this.userDAO.delete(idToDelete);
			verify(this.template, times(1)).remove(Query.query(Criteria.where("id").is(idToDelete)), UserDTO.class);
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void delete_by_key_NullParam() {
		try {
			this.userDAO.delete((String)null);
			fail("L'utilisateur possédant une clef [null] n'aurait pas du être supprimé.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}
	}

	@Test
	public void delete_by_user() {
		UserDTO userToDelete = UserDTO.with()
				.login("nletteron")
				.password("TEST")
				.create();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}			
		}).when(this.template).remove(userToDelete);
		try {
			this.userDAO.delete(userToDelete);
			assertThat(userToDelete).isNotNull();
			verify(this.template, times(1)).remove(userToDelete);;
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}	
	}

	@Test
	public void delete_all() {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}			
		}).when(this.template).dropCollection(UserDTO.class);	
		try {
			this.userDAO.deleteAll();
			verify(this.template, times(1)).dropCollection(UserDTO.class);
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	@Test
	public void update_user() {
		UserDTO userToUpdate = UserDTO.with()
				.id(ID_SEQUENCER_TEST)
				.login("nletteron")
				.password("TEST")
				.create();		
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}	
		}).when(this.template).updateFirst((Query)any(), (Update)any(), (Class<?>)any());
		try {
			this.userDAO.update(userToUpdate);
			verify(this.template, times(1)).updateFirst((Query)any(), (Update)any(), (Class<?>)any());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void update_user_NullParam() {
		UserDTO userToUpdate = null;		
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}	
		}).when(this.template).updateFirst((Query)any(), (Update)any(), (Class<?>)any());
		try {
			this.userDAO.update(userToUpdate);
			fail("La mise à jour d'un utilisateur [null] aurait dû être impossible.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}		
	}
	
	@Test
	public void update_user_WithoutKey() {
		UserDTO userToUpdate = UserDTO.with()
				.login("nletteron")
				.password("TEST")
				.create();		
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}	
		}).when(this.template).updateFirst((Query)any(), (Update)any(), (Class<?>)any());
		try {
			this.userDAO.update(userToUpdate);
			fail("La mise à jour d'un utilisateur sans clef/id aurait dû être impossible.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void read_by_user() {
		UserDTO userDTO = UserDTO.with().login("nletteron").create();
		List<UserDTO> users = new ArrayList<UserDTO>();
		users.add(UserDTO.with().login("nletteron").password("1").surname("test").create());
		users.add(UserDTO.with().login("nletteron").password("2").create());
		users.add(UserDTO.with().login("nletteron").password("3").role(UserRoles.ADMIN).create());
		when((List<UserDTO>)this.template.find((Query)any(), (Class<UserDTO>)any())).thenReturn(users);	
		try { 
			List<UserDTO> foundUsers = this.userDAO.read(userDTO);
			assertThat(foundUsers).isNotNull().isNotEmpty();
			assertThat(foundUsers).isEqualTo(users);
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void read_by_key() {
		UserDTO userDTO = UserDTO.with().id(ID_SEQUENCER_TEST)
				.login("nletteron")
				.password("password")
				.name("name")
				.create();
		Query query = new Query(Criteria.where("id").is(ID_SEQUENCER_TEST));
		when(this.template.findOne(query, UserDTO.class)).thenReturn(userDTO);
		try {
			UserDTO foundUser = this.userDAO.read(ID_SEQUENCER_TEST);
			assertThat(foundUser).isNotNull();
			assertThat(foundUser.getId()).isEqualTo(ID_SEQUENCER_TEST);
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
	
	@Test
	public void read_by_nullKey() {
		try {
			this.userDAO.read((String)null);
			fail("L'utilisateur [null] n'aurait pas du être lu.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}		
	}
	
	@Test
	public void read_by_nullUser() {
		try {
			this.userDAO.read((UserDTO)null);
			fail("L'utilisateur [null] n'aurait pas du être lu.");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
		}		
	}
	
}
