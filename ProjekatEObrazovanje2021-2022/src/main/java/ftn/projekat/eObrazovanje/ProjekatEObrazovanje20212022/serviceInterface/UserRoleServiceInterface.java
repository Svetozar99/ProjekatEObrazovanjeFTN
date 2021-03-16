package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;

public interface UserRoleServiceInterface {
	
	public List<UserRole> findAll();
	
	public UserRole findById(Long id);
	
	public UserRole save(UserRole userRole);
	
	public void delete(Long id);
}
