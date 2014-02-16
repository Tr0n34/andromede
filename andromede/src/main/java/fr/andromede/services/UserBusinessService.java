package fr.andromede.services;

import org.springframework.dao.DataAccessException;

import fr.andromede.common.exceptions.BusinessServiceException;
import fr.andromede.entity.common.impl.User;

public interface UserBusinessService extends BusinessService {

	public void saveUser(User user) throws BusinessServiceException, DataAccessException;

	public User retrieveUser(String key) throws BusinessServiceException, DataAccessException;

	public User createUser(User user) throws BusinessServiceException, DataAccessException;

}
