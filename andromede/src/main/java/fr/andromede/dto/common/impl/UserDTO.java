package fr.andromede.dto.common.impl;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.dto.DTO;
import fr.andromede.dto.common.UserDTOFluent;
import fr.andromede.fluent.ObjectFluentBuilder;

public class UserDTO implements DTO {

	private String id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private UserRoles role;

	public static UserDTOFluent with() {
		return (UserDTOFluent)ObjectFluentBuilder.create(new UserDTO(), UserDTOFluent.class);
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public DTO id(String id) {
		this.setId(id);
		return this;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

}
