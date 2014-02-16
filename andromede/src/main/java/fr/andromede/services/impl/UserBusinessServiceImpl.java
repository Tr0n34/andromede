package fr.andromede.services.impl;

import javax.annotation.Resource;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.andromede.common.exceptions.BusinessServiceException;
import fr.andromede.common.utils.CustomMapper;
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
	private MapperFacade mapper;
	
	public void init() {
		
	}
	
	public void saveUser(User user) throws BusinessServiceException, DataAccessException {
	}

	public User retrieveUser(String key) throws BusinessServiceException, DataAccessException {
		return null;
	}

	public User createUser(User user) throws BusinessServiceException, DataAccessException {
		LOGGER.trace("START  -- createUser(User)");
		LOGGER.trace(Utils.toString(user));
		UserDTO userDTO = this.mapper.map(user, UserDTO.class);
		User createdUser = this.mapper.map(this.userDAO.create(userDTO), User.class);
		LOGGER.trace(Utils.toString(createdUser));
		LOGGER.trace("END    -- createUser(User");
		return createdUser;
	}

}
