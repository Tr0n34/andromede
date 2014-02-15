package fr.andromede.common.enums;

public enum UserRoles {

	NONE(-1, "ROLE_NONE"), 
	ADMIN(1, "ROLE_USER"), 
	USER(2, "ROLE_ADMIN");

	private int role;
	private String label;

	private UserRoles(int role, String label) { this.role = role;
	this.label = label; }

	public int intValue()
	{
		return this.role;
	}

	public String getLabel() {
		return this.label;
	}

}
