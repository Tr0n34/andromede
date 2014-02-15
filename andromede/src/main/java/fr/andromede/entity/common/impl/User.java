package fr.andromede.entity.common.impl;

import fr.andromede.common.enums.UserRoles;
import fr.andromede.entity.Entity;
import fr.andromede.entity.common.UserFluent;
import fr.andromede.fluent.ObjectFluentBuilder;

public class User implements Entity {

	private String login;
	private String password;
	private String name;
	private String surname;
	public UserRoles role;

	public static UserFluent with() {
		return (UserFluent)ObjectFluentBuilder.create(new User(), UserFluent.class);
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserRoles getRole() {
		return this.role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

}
