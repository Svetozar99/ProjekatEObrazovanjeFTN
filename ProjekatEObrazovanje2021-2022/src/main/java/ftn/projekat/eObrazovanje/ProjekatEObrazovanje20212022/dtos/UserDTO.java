package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private RoleDTO roleDTO;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String firstName, String lastName, String userName, String password, RoleDTO roleDTO) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.roleDTO = roleDTO;
	}
	
	public UserDTO(User user) {
		this(user.getId(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),new RoleDTO(user.getRole()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	
}
