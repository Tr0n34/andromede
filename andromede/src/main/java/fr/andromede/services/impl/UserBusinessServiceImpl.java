package fr.andromede.services.impl;

import javax.annotation.Resource;

import ma.glasnost.orika.MapperFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.andromede.common.exceptions.BusinessServiceException;
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
	
	
	public void saveUser(User user) throws BusinessServiceException, DataAccessException {
	}

	public User retrieveUser(String key) throws BusinessServiceException, DataAccessException {
		return null;
	}

	public User createUser(User user) throws BusinessServiceException, DataAccessException {
		LOGGER.trace("START  -- createUser(User)");
		LOGGER.trace(Utils.toString(user));
		User createdUser = null;
		if ( user != null ) {
			if ( user.getLogin() != null && !"".equals(user.getLogin().trim()) ) {
				if ( user.getPassword() != null && !"".equals(user.getPassword().trim()) ) {
					UserDTO userDTO = this.mapper.map(user, UserDTO.class);
					createdUser = this.mapper.map(this.userDAO.create(userDTO), User.class);
					LOGGER.trace(Utils.toString(createdUser));
				} else {
					throw new BusinessServiceException("USR-BS-003", "Impossible de créer un utilisateur de valeur avec un password [null] ou [vide].");
				}
			} else {
				throw new BusinessServiceException("USR-BS-002", "Impossible de créer un utilisateur de valeur avec un login [null] ou [vide].");
			}
		} else {
			throw new BusinessServiceException("USR-BS-001", "Impossible de créer un utilisateur de valeur [null].");
		}
		LOGGER.trace("END    -- createUser(User");
		return createdUser;
	}

}
