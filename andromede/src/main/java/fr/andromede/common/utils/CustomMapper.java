package fr.andromede.common.utils;

import fr.andromede.dto.common.impl.UserDTO;
import fr.andromede.entity.common.impl.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class CustomMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		super.configure(factory);
		
		factory.registerClassMap(factory.classMap(User.class, UserDTO.class)
				.field("login", "login")
				.field("password", "password")
				.field("name", "name")
				.field("surname", "surname")
				.field("role", "role"));

	}

	
	
}
