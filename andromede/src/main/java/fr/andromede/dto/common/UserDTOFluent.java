package fr.andromede.dto.common;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.dto.common.impl.UserDTO;

public interface UserDTOFluent {

	public UserDTOFluent login(String login);

	public UserDTOFluent password(String password);

	public UserDTOFluent name(String name);

	public UserDTOFluent surname(String surname);

	public UserDTOFluent role(UserRoles role);

	public UserDTO create();	

	
}
