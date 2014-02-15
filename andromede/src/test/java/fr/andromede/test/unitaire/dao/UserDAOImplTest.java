package fr.andromede.test.unitaire.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.util.ReflectionTestUtils;

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
			assertThat(e).isInstanceOf(DataRetrievalFailureException.class);
		}
	}

	@Test
	public void delete_by_user() {
		
	}

	@Test
	public void delete_all() {
		
	}

	@Test
	public void update_user() {
		
	}

	@Test
	public void read_by_user() {
		
	}
	
	@Test
	public void read_by_key() {
		
	}
	
}
