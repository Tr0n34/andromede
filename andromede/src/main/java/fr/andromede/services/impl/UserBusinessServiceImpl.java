package fr.andromede.services.impl;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.andromede.common.utils.Utils;
import fr.andromede.dao.UserDAO;
import fr.andromede.dto.common.impl.UserDTO;
import fr.andromede.entity.common.impl.User;
import fr.andromede.services.UserBusinessService;

@Component("userBS")
public class UserBusinessServiceImpl implements UserBusinessService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Resource(name="mapper")
	private Mapper mapper;

	public Mapper getMapper() {
		return this.mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public void saveUser(User user)
			throws DataAccessException {
	}

	public User retrieveUser(String key) throws DataAccessException {
		LOGGER.trace("START  -- retrieve({})", key);
		UserDTO userDTO = (UserDTO)this.userDAO.read(key);
		User user = (User)this.mapper.map(userDTO, User.class);
		LOGGER.debug("Utilisateur trouvé : {}.", Utils.toString(user));
		LOGGER.trace("END    -- retrieve({})", key);
		return user;
	}

	public User createUser(User user) throws DataAccessException {
		return null;
	}

}
