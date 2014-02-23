package fr.andromede.common.utils;

import fr.andromede.dto.common.impl.UserDTO;
import fr.andromede.entity.common.impl.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.ClassMapBuilder;

public class CustomMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		super.configure(factory);

		factory.registerClassMap(mapUser(factory));

	}
	
	private ClassMapBuilder<User, UserDTO> mapUser(MapperFactory factory) {
		return factory.classMap(User.class, UserDTO.class)
					.field("login", "login")
					.field("password", "password")
					.field("name", "name")
					.field("surname", "surname")
					.field("role", "role");
	}

	
	
}
