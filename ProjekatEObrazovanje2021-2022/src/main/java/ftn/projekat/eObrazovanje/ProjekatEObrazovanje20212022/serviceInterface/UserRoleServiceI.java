package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;

public interface UserRoleServiceI {
	
	public List<Role> findAll();
	
	public Role findById(Long id);
	
	public Role save(Role userRole);
	
	public void delete(Long id);
}
