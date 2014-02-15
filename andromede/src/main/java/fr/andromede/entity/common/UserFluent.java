package fr.andromede.entity.common;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.entity.common.impl.User;

public interface UserFluent {

	public UserFluent login(String login);

	public UserFluent password(String password);

	public UserFluent name(String name);

	public UserFluent surname(String surname);

	public UserFluent role(UserRoles role);

	public User create();	

}
